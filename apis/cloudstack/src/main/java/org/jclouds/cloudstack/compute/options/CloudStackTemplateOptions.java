/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.cloudstack.compute.options;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import org.jclouds.cloudstack.options.DeployVirtualMachineOptions;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.options.TemplateOptions;
import org.jclouds.io.Payload;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Contains options supported by the
 * {@link ComputeService#createNodesInGroup(String, int, TemplateOptions)} and
 * {@link ComputeService#createNodesInGroup(String, int, TemplateOptions)}
 * operations on the <em>gogrid</em> provider.
 * 
 * <h2>Usage</h2> The recommended way to instantiate a
 * {@link CloudStackTemplateOptions} object is to statically import
 * {@code CloudStackTemplateOptions.*} and invoke a static creation method
 * followed by an instance mutator (if needed):
 * <p>
 * 
 * <pre>
 * import static org.jclouds.compute.options.CloudStackTemplateOptions.Builder.*;
 * ComputeService client = // get connection
 * templateBuilder.options(inboundPorts(22, 80, 8080, 443));
 * Set&lt;? extends NodeMetadata&gt; set = client.createNodesInGroup(tag, 2, templateBuilder.build());
 * </pre>
 * 
 * @author Adrian Cole
 */
public class CloudStackTemplateOptions extends TemplateOptions implements Cloneable {

   protected Set<Long> securityGroupIds = Sets.<Long> newLinkedHashSet();
   protected Set<Long> networkIds = Sets.<Long> newLinkedHashSet();
   protected Map<String, Long> ipsToNetworks = Maps.<String, Long>newLinkedHashMap();
   protected String ipOnDefaultNetwork;
   protected String keyPair;
   protected boolean setupStaticNat = true;

   @Override
   public CloudStackTemplateOptions clone() {
      CloudStackTemplateOptions options = new CloudStackTemplateOptions();
      copyTo(options);
      return options;
   }

   @Override
   public void copyTo(TemplateOptions to) {
      super.copyTo(to);
      if (to instanceof CloudStackTemplateOptions) {
         CloudStackTemplateOptions eTo = CloudStackTemplateOptions.class.cast(to);
         eTo.securityGroupIds(this.securityGroupIds);
         eTo.networkIds(this.networkIds);
         eTo.ipsToNetworks(this.ipsToNetworks);
         eTo.ipOnDefaultNetwork(this.ipOnDefaultNetwork);
         eTo.keyPair(this.keyPair);
         eTo.setupStaticNat(setupStaticNat);
      }
   }

   /**
    * @see DeployVirtualMachineOptions#securityGroupId
    */
   public CloudStackTemplateOptions securityGroupId(long securityGroupId) {
      this.securityGroupIds.add(securityGroupId);
      return this;
   }

   /**
    * @see DeployVirtualMachineOptions#securityGroupIds
    */
   public CloudStackTemplateOptions securityGroupIds(Iterable<Long> securityGroupIds) {
      Iterables.addAll(this.securityGroupIds, checkNotNull(securityGroupIds, "securityGroupIds was null"));
      return this;
   }

   public Set<Long> getSecurityGroupIds() {
      return securityGroupIds;
   }

   /**
    * @see DeployVirtualMachineOptions#networkId
    */
   public CloudStackTemplateOptions networkId(long networkId) {
      this.networkIds.add(networkId);
      return this;
   }

   /**
    * @see DeployVirtualMachineOptions#networkIds
    */
   public CloudStackTemplateOptions networkIds(Iterable<Long> networkIds) {
      Iterables.addAll(this.networkIds, checkNotNull(networkIds, "networkIds was null"));
      return this;
   }

   public Set<Long> getNetworkIds() {
      return networkIds;
   }

   public CloudStackTemplateOptions setupStaticNat(boolean setupStaticNat) {
      this.setupStaticNat = setupStaticNat;
      return this;
   }

   public boolean shouldSetupStaticNat() {
      return this.setupStaticNat;
   }

   /**
    * @see DeployVirtualMachineOptions#ipOnDefaultNetwork
    */
   public CloudStackTemplateOptions ipOnDefaultNetwork(String ipOnDefaultNetwork) {
      this.ipOnDefaultNetwork = ipOnDefaultNetwork;
      return this;
   }

   public String getIpOnDefaultNetwork() {
      return ipOnDefaultNetwork;
   }

   /**
    * @see DeployVirtualMachineOptions#ipOnDefaultNetwork(String)
    */
   public CloudStackTemplateOptions ipsToNetworks(Map<String, Long> ipsToNetworks) {
      this.ipsToNetworks.putAll(ipsToNetworks);
      return this;
   }

   public Map<String, Long> getIpsToNetworks() {
      return ipsToNetworks;
   }

   /**
    * @see DeployVirtualMachineOptions#keyPair(String)
    */
   public CloudStackTemplateOptions keyPair(String keyPair) {
      this.keyPair = keyPair;
      return this;
   }

   public String getKeyPair() {
      return keyPair;
   }

   public static final CloudStackTemplateOptions NONE = new CloudStackTemplateOptions();

   public static class Builder {

      /**
       * @see CloudStackTemplateOptions#securityGroupId
       */
      public static CloudStackTemplateOptions securityGroupId(long id) {
         CloudStackTemplateOptions options = new CloudStackTemplateOptions();
         return options.securityGroupId(id);
      }

      /**
       * @see CloudStackTemplateOptions#securityGroupIds
       */
      public static CloudStackTemplateOptions securityGroupIds(Iterable<Long> securityGroupIds) {
         CloudStackTemplateOptions options = new CloudStackTemplateOptions();
         return options.securityGroupIds(securityGroupIds);
      }

      /**
       * @see CloudStackTemplateOptions#networkId
       */
      public static CloudStackTemplateOptions networkId(long id) {
         CloudStackTemplateOptions options = new CloudStackTemplateOptions();
         return options.networkId(id);
      }

      /**
       * @see CloudStackTemplateOptions#networkIds
       */
      public static CloudStackTemplateOptions networkIds(Iterable<Long> networkIds) {
         CloudStackTemplateOptions options = new CloudStackTemplateOptions();
         return options.networkIds(networkIds);
      }

      /**
       * @see CloudStackTemplateOptions#ipOnDefaultNetwork
       */
      public static CloudStackTemplateOptions ipOnDefaultNetwork(String ipAddress) {
         CloudStackTemplateOptions options = new CloudStackTemplateOptions();
         return options.ipOnDefaultNetwork(ipAddress);
      }

      /**
       * @see CloudStackTemplateOptions#ipsToNetworks
       */
      public static CloudStackTemplateOptions ipsToNetworks(Map<String, Long> ipToNetworkMap) {
         CloudStackTemplateOptions options = new CloudStackTemplateOptions();
         return options.ipsToNetworks(ipToNetworkMap);
      }

      /**
       * @see CloudStackTemplateOptions#setupStaticNat
       */
      public static CloudStackTemplateOptions setupStaticNat(boolean setupStaticNat) {
         CloudStackTemplateOptions options = new CloudStackTemplateOptions();
         return options.setupStaticNat(setupStaticNat);
      }

      /**
       * @see CloudStackTemplateOptions#keyPair
       */
      public static CloudStackTemplateOptions keyPair(String keyPair) {
         CloudStackTemplateOptions options = new CloudStackTemplateOptions();
         return options.keyPair(keyPair);
      }

      // methods that only facilitate returning the correct object type

      /**
       * @see TemplateOptions#inboundPorts(int...)
       */
      public static CloudStackTemplateOptions inboundPorts(int... ports) {
         CloudStackTemplateOptions options = new CloudStackTemplateOptions();
         return CloudStackTemplateOptions.class.cast(options.inboundPorts(ports));
      }

      /**
       * @see TemplateOptions#blockOnPort(int, int)
       */
      public static CloudStackTemplateOptions blockOnPort(int port, int seconds) {
         CloudStackTemplateOptions options = new CloudStackTemplateOptions();
         return CloudStackTemplateOptions.class.cast(options.blockOnPort(port, seconds));
      }

      /**
       * @see TemplateOptions#runScript(Payload)
       */
      public static CloudStackTemplateOptions runScript(Payload script) {
         CloudStackTemplateOptions options = new CloudStackTemplateOptions();
         return CloudStackTemplateOptions.class.cast(options.runScript(script));
      }

      /**
       * @see TemplateOptions#userMetadata(Map)
       */
      public static CloudStackTemplateOptions userMetadata(Map<String, String> userMetadata) {
         CloudStackTemplateOptions options = new CloudStackTemplateOptions();
         return CloudStackTemplateOptions.class.cast(options.userMetadata(userMetadata));
      }

      /**
       * @see TemplateOptions#userMetadata(String, String)
       */
      public static CloudStackTemplateOptions userMetadata(String key, String value) {
         CloudStackTemplateOptions options = new CloudStackTemplateOptions();
         return CloudStackTemplateOptions.class.cast(options.userMetadata(key, value));
      }
   }

   // methods that only facilitate returning the correct object type

   /**
    * @see TemplateOptions#blockOnPort(int, int)
    */
   @Override
   public CloudStackTemplateOptions blockOnPort(int port, int seconds) {
      return CloudStackTemplateOptions.class.cast(super.blockOnPort(port, seconds));
   }

   /**
    * @see TemplateOptions#inboundPorts(int...)
    */
   @Override
   public CloudStackTemplateOptions inboundPorts(int... ports) {
      return CloudStackTemplateOptions.class.cast(super.inboundPorts(ports));
   }

   /**
    * @see TemplateOptions#authorizePublicKey(String)
    */
   @Override
   public CloudStackTemplateOptions authorizePublicKey(String publicKey) {
      return CloudStackTemplateOptions.class.cast(super.authorizePublicKey(publicKey));
   }

   /**
    * @see TemplateOptions#installPrivateKey(String)
    */
   @Override
   public CloudStackTemplateOptions installPrivateKey(String privateKey) {
      return CloudStackTemplateOptions.class.cast(super.installPrivateKey(privateKey));
   }

   /**
    * @see TemplateOptions#runScript(Payload)
    */
   @Deprecated
   @Override
   public CloudStackTemplateOptions runScript(Payload script) {
      return CloudStackTemplateOptions.class.cast(super.runScript(script));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public CloudStackTemplateOptions userMetadata(Map<String, String> userMetadata) {
      return CloudStackTemplateOptions.class.cast(super.userMetadata(userMetadata));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public CloudStackTemplateOptions userMetadata(String key, String value) {
      return CloudStackTemplateOptions.class.cast(super.userMetadata(key, value));
   }
}
