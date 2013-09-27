/*
 * The MIT License
 * 
 * Copyright (c) 2013 IKEDA Yasuyuki
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package jp.ikedam.jenkins.plugins.quickfix16255;

import java.io.IOException;

import jenkins.model.Jenkins;

import hudson.EnvVars;
import hudson.model.Hudson;
import hudson.model.Node;
import hudson.model.Slave;
import hudson.model.TaskListener;
import hudson.slaves.NodeProperty;
import hudson.slaves.SlaveComputer;

/**
 *
 */
public class CasedSlaveComputer extends SlaveComputer
{

    public CasedSlaveComputer(Slave slave)
    {
        super(slave);
    }
    
    @Override
    public EnvVars getEnvironment() throws IOException, InterruptedException
    {
        return CasedEnvVars.getRemote(getChannel());
    }
    
    @Override
    public EnvVars buildEnvironment(TaskListener listener) throws IOException, InterruptedException
    {
        EnvVars env = new CasedEnvVars();
        
        Node node = getNode();
        if (node==null)     return env; // bail out
        
        for (NodeProperty<?> nodeProperty: Jenkins.getInstance().getGlobalNodeProperties()) {
            nodeProperty.buildEnvVars(env,listener);
        }
        
        for (NodeProperty<?> nodeProperty: node.getNodeProperties()) {
            nodeProperty.buildEnvVars(env,listener);
        }
        
        // TODO: hmm, they don't really belong
        String rootUrl = Hudson.getInstance().getRootUrl();
        if(rootUrl!=null) {
            env.put("HUDSON_URL", rootUrl); // Legacy.
            env.put("JENKINS_URL", rootUrl);
        }
        
        return env;
    }
}
