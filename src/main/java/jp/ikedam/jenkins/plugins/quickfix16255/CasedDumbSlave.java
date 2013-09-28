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
import java.util.List;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.Computer;
import hudson.model.Slave;
import hudson.model.Descriptor.FormException;
import hudson.slaves.ComputerLauncher;
import hudson.slaves.DumbSlave;
import hudson.slaves.NodeProperty;
import hudson.slaves.RetentionStrategy;

/**
 *
 */
public class CasedDumbSlave extends Slave
{
    private static final long serialVersionUID = 1326261832757440102L;
    
    @DataBoundConstructor
    public CasedDumbSlave(String name, String nodeDescription, String remoteFS, String numExecutors, Mode mode, String labelString, ComputerLauncher launcher, @SuppressWarnings("rawtypes") RetentionStrategy retentionStrategy, List<? extends NodeProperty<?>> nodeProperties) throws IOException, FormException
    {
        super(name, nodeDescription, remoteFS, numExecutors, mode, labelString, launcher, retentionStrategy, nodeProperties);
    }
    
    @Override
    public Computer createComputer()
    {
        return new CasedSlaveComputer(this);
    }
    
    @Extension
    public static final class DescriptorImpl extends SlaveDescriptor
    {
        public String getDisplayName() {
            return "DumbSlave with Case Sensitive Environment Variables";
        }
        
        @Override
        public String getConfigPage()
        {
            return getViewPage(DumbSlave.class, "configure-entries.jelly");
        }
        
        public Class<?> getClazz()
        {
            return DumbSlave.class;
        }
    }
}
