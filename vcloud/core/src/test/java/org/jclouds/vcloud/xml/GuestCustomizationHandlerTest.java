/**
 *
 * Copyright (C) 2010 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.jclouds.vcloud.xml;

import static org.testng.Assert.assertEquals;

import java.io.InputStream;
import java.net.URI;
import java.net.UnknownHostException;

import org.jclouds.http.functions.BaseHandlerTest;
import org.jclouds.vcloud.VCloudMediaType;
import org.jclouds.vcloud.domain.GuestCustomization;
import org.jclouds.vcloud.domain.internal.ReferenceTypeImpl;
import org.testng.annotations.Test;

/**
 * Tests behavior of {@code GuestCustomizationSectionHandler}
 * 
 * @author Adrian Cole
 */
@Test(groups = "unit", testName = "vcloud.GuestCustomizationSectionHandlerTest")
public class GuestCustomizationHandlerTest extends BaseHandlerTest {

   public void testDefault() throws UnknownHostException {
      InputStream is = getClass().getResourceAsStream("/guestCustomization.xml");

      GuestCustomization result = factory.create(injector.getInstance(GuestCustomizationHandler.class)).parse(is);

      checkGuestCustomization(result);

   }

   @Test(enabled = false)
   public static void checkGuestCustomization(GuestCustomization result) {
      assertEquals(result.getType(), VCloudMediaType.GUESTCUSTOMIZATIONSECTION_XML);
      assertEquals(result.getHref(), URI
               .create("https://vcenterprise.bluelock.com/api/v1.0/vApp/vm-2087535248/guestCustomizationSection/"));
      assertEquals(result.getInfo(), "Specifies Guest OS Customization Settings");
      assertEquals(result.isEnabled(), new Boolean(true));
      assertEquals(result.shouldChangeSid(), new Boolean(false));
      assertEquals(result.getVirtualMachineId(), "2087535248");
      assertEquals(result.isJoinDomainEnabled(), new Boolean(false));
      assertEquals(result.useOrgSettings(), new Boolean(false));
      assertEquals(result.getDomainName(), null);
      assertEquals(result.getDomainUserName(), null);
      assertEquals(result.getDomainUserPassword(), null);
      assertEquals(result.isAdminPasswordEnabled(), new Boolean(true));
      assertEquals(result.isAdminPasswordAuto(), new Boolean(true));
      assertEquals(result.getAdminPassword(), null);
      assertEquals(result.isResetPasswordRequired(), new Boolean(false));
      assertEquals(
               result.getCustomizationScript(),
               "#!/bin/bash if [[ $1 == \"postcustomization\" ]]; then echo \"post customization\" touch /root/.postcustomization ping www.redhat.com -c 1 sleep 30 # register with RHN /usr/sbin/rhnreg_ks --profilename vic_`hostname`_`dmidecode -s system-uuid` --activationkey=XXXXXXXXXXXX --force echo \"rhn registered\" # make hostname fully qualified to speed up sendmail start perl -i -pe \"s/`hostname`/`hostname`.victory.blk/g\" /etc/sysconfig/network rm /etc/ssh/*_key* service sshd restart echo \"completed\" fi");
      assertEquals(result.getComputerName(), "RHEL5");
      assertEquals(result.getEdit(), new ReferenceTypeImpl(null, VCloudMediaType.GUESTCUSTOMIZATIONSECTION_XML, URI
               .create("https://vcenterprise.bluelock.com/api/v1.0/vApp/vm-2087535248/guestCustomizationSection/")));
   }
}
