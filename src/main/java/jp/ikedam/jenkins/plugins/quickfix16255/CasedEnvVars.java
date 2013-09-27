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
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import hudson.EnvVars;
import hudson.Main;
import hudson.remoting.Callable;
import hudson.remoting.VirtualChannel;

/**
 *
 */
public class CasedEnvVars extends EnvVars
{
    private static final long serialVersionUID = -6987927066200377405L;
    
    private TreeMap<String,String> envVars = new TreeMap<String,String>();
    
    public CasedEnvVars()
    {
    }
    
    public CasedEnvVars(Map<String,String> m)
    {
        this();
        putAll(m);
    }
    
    public CasedEnvVars(EnvVars m)
    {
        this((Map<String,String>)m);
    }
    
    @Override
    public java.util.Map.Entry<String, String> ceilingEntry(String key)
    {
        return envVars.ceilingEntry(key);
    }
    
    @Override
    public String ceilingKey(String key)
    {
        return envVars.ceilingKey(key);
    }
    
    @Override
    public void clear()
    {
        envVars.clear();
    }
    
    @Override
    public Comparator<? super String> comparator()
    {
        return envVars.comparator();
    }
    
    @Override
    public boolean containsKey(Object key)
    {
        return envVars.containsKey(key);
    }
    
    @Override
    public boolean containsValue(Object value)
    {
        return envVars.containsValue(value);
    }
    
    @Override
    public NavigableSet<String> descendingKeySet()
    {
        return envVars.descendingKeySet();
    }
    
    @Override
    public NavigableMap<String, String> descendingMap()
    {
        return envVars.descendingMap();
    }
    
    @Override
    public Set<java.util.Map.Entry<String, String>> entrySet()
    {
        return envVars.entrySet();
    }
    
    @Override
    public java.util.Map.Entry<String, String> firstEntry()
    {
        return envVars.firstEntry();
    }
    
    @Override
    public String firstKey()
    {
        return envVars.firstKey();
    }
    
    @Override
    public java.util.Map.Entry<String, String> floorEntry(String key)
    {
        return envVars.floorEntry(key);
    }
    
    @Override
    public String floorKey(String key)
    {
        return envVars.floorKey(key);
    }
    
    @Override
    public String get(Object key)
    {
        return envVars.get(key);
    }
    
    @Override
    public SortedMap<String, String> headMap(String toKey)
    {
        return envVars.headMap(toKey);
    }
    
    @Override
    public NavigableMap<String, String> headMap(String toKey, boolean inclusive)
    {
        return envVars.headMap(toKey, inclusive);
    }
    @Override
    public java.util.Map.Entry<String, String> higherEntry(String key)
    {
        return envVars.higherEntry(key);
    }
    
    @Override
    public String higherKey(String key)
    {
        return envVars.higherKey(key);
    }
    
    @Override
    public Set<String> keySet()
    {
        return envVars.keySet();
    }
    
    @Override
    public java.util.Map.Entry<String, String> lastEntry()
    {
        return envVars.lastEntry();
    }
    
    @Override
    public String lastKey()
    {
        return envVars.lastKey();
    }
    
    @Override
    public java.util.Map.Entry<String, String> lowerEntry(String key)
    {
        return envVars.lowerEntry(key);
    }
    
    @Override
    public String lowerKey(String key)
    {
        return envVars.lowerKey(key);
    }
    
    @Override
    public NavigableSet<String> navigableKeySet()
    {
        return envVars.navigableKeySet();
    }
    
    @Override
    public java.util.Map.Entry<String, String> pollFirstEntry()
    {
        return envVars.pollFirstEntry();
    }
    
    @Override
    public java.util.Map.Entry<String, String> pollLastEntry()
    {
        return envVars.pollLastEntry();
    }
    
    @Override
    public String put(String key, String value)
    {
        return envVars.put(key, value);
    }
    
    @Override
    public void putAll(Map<? extends String, ? extends String> map)
    {
        envVars.putAll(map);
    }
    
    @Override
    public String remove(Object key)
    {
        return envVars.remove(key);
    }
    
    @Override
    public int size()
    {
        return envVars.size();
    }
    
    @Override
    public NavigableMap<String, String> subMap(String fromKey,
            boolean fromInclusive, String toKey, boolean toInclusive)
    {
        return envVars.subMap(fromKey, fromInclusive, toKey, toInclusive);
    }
    
    @Override
    public SortedMap<String, String> subMap(String fromKey, String toKey)
    {
        return envVars.subMap(fromKey, toKey);
    }
    
    @Override
    public SortedMap<String, String> tailMap(String fromKey)
    {
        return envVars.tailMap(fromKey);
    }
    
    @Override
    public NavigableMap<String, String> tailMap(String fromKey,
            boolean inclusive)
    {
        return envVars.tailMap(fromKey, inclusive);
    }
    
    @Override
    public Collection<String> values()
    {
        return envVars.values();
    }
    
    /**
     * Obtains the environment variables of a remote peer.
     *
     * @param channel
     *      Can be null, in which case the map indicating "N/A" will be returned.
     * @return
     *      A fresh copy that can be owned and modified by the caller.
     */
    public static EnvVars getRemote(VirtualChannel channel) throws IOException, InterruptedException {
        if(channel==null)
        {
            EnvVars envVars = new CasedEnvVars();
            envVars.put("N/A", "N/A");
            return envVars;
        }
        return channel.call(new GetEnvVars());
    }
    
    private static final class GetEnvVars implements Callable<EnvVars,RuntimeException> {
        public EnvVars call() {
            return new CasedEnvVars(EnvVars.masterEnvVars);
        }
        private static final long serialVersionUID = 1L;
    }
    
    public static final Map<String,String> masterEnvVars = initMaster();
    
    private static EnvVars initMaster() {
        EnvVars vars = new CasedEnvVars(System.getenv());
        //vars.platform = Platform.current();
        if(Main.isUnitTest || Main.isDevelopmentMode)
            // if unit test is launched with maven debug switch,
            // we need to prevent forked Maven processes from seeing it, or else
            // they'll hang
            vars.remove("MAVEN_OPTS");
        return vars;
    }
}
