package com.pnfsoftware.jeb.client;

import com.pnfsoftware.jeb.client.api.IClientContext;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.events.ControllerNotification;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.io.File;
import java.io.IOException;

@SerDisabled
public class HeadlessClientContext extends AbstractClientContext implements IClientContext {
   private static final ILogger logger = GlobalLog.getLogger(HeadlessClientContext.class);
   private static final String _yes = "yes";

   @Override
   public void initialize(String[] var1) {
      super.initialize(var1);
   }

   @Override
   public void start() throws JebException {
      super.start();
      this.initializeEngines();
   }

   @Override
   public void stop() {
      super.stop();
      logger.info(S.L("Done."));
   }

   @Override
   public boolean displayEula(String var1) {
      System.out.println(var1);
      System.out.format(S.L("Write \"%s\" to agree, then press enter") + ": ", "yes");
      String var2 = IO.readInputLineSafe();
      return var2 != null && var2.trim().equalsIgnoreCase("yes");
   }

   @Override
   public String retrieveLicenseKey(String var1) {
      System.out.println(S.L("Please generate a license key to use JEB. This one-time operation will only take a few seconds."));
      System.out
         .println(Strings.ff(S.L("Please visit %s, and use the following \"license data\" blob to generate a key"), "https://www.pnfsoftware.com/genlk"));

      try {
         File var2 = new File(".jeb_license_data");
         IO.writeFile(var2, var1);
         System.out.format(S.L("For your reference, the license data blob was dumped to: %s\n"), var2.getAbsolutePath());
      } catch (IOException var3) {
      }

      System.out.println(Strings.ff(S.L("License data: %s"), var1));
      System.out.print(Strings.ff("%s: ", S.L("Input your license key")));
      return IO.readInputLineSafe();
   }

   @Override
   public boolean checkUpdate() {
      return false;
   }

   @Override
   public void onUpdatedSoftware(String var1, Version var2) {
   }

   @Override
   public void notifySupportExpired() {
   }

   @Override
   public void displayDemoInformation(String var1) {
   }

   @Override
   public boolean setupController() {
      logger.warn(S.L("[Floating] Please edit jeb-client.cfg and set up your controller interface"));
      return false;
   }

   @Override
   public void notifyFloatingClient(ControllerNotification var1) {
      logger.error(S.L("[Floating] The JEB controller disallowed this instance to run"));
   }
}
