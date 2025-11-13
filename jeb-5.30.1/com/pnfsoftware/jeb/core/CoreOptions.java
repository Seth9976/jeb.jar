package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.util.net.NetProxyInfo;
import java.io.File;

public class CoreOptions {
   private ControllerInfo ctlInfo;
   private NetProxyInfo proxyInfo;
   private boolean allowAsynchronousProcessing;
   private boolean developmentMode;
   private File jebClassesLocation;

   private CoreOptions() {
   }

   public void setControllerInfo(ControllerInfo var1) {
      this.ctlInfo = var1;
   }

   public ControllerInfo getControllerInfo() {
      return this.ctlInfo;
   }

   public void setAllowAsynchronousProcessing(boolean var1) {
      this.allowAsynchronousProcessing = var1;
   }

   public boolean isAllowAsynchronousProcessing() {
      return this.allowAsynchronousProcessing;
   }

   public void setUIClient(boolean var1) {
      this.setAllowAsynchronousProcessing(var1);
   }

   public boolean isUIClient() {
      return this.isAllowAsynchronousProcessing();
   }

   public void setDevelopmentMode(boolean var1) {
      this.developmentMode = var1;
   }

   public boolean isDevelopmentMode() {
      return this.developmentMode;
   }

   public void setJebClassesLocation(File var1) {
      this.jebClassesLocation = var1;
   }

   public File getJebClassesLocation() {
      return this.jebClassesLocation;
   }

   public void setStandardProxyInfo(NetProxyInfo var1) {
      this.proxyInfo = var1;
   }

   public NetProxyInfo getStandardProxyInfo() {
      return this.proxyInfo;
   }

   public static CoreOptions getDefault() {
      return new CoreOptions();
   }
}
