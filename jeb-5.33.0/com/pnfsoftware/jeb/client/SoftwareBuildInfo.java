package com.pnfsoftware.jeb.client;

import com.pnfsoftware.jeb.core.Version;

public class SoftwareBuildInfo {
   Version version;
   int channel;
   int flags;

   public SoftwareBuildInfo() {
   }

   public SoftwareBuildInfo(Version var1, int var2, int var3) {
      this.version = var1;
      this.channel = var2;
      this.flags = var3;
   }

   public void setVersion(Version var1) {
      this.version = var1;
   }

   public Version getVersion() {
      return this.version;
   }

   public void setChannel(int var1) {
      this.channel = var1;
   }

   public int getChannel() {
      return this.channel;
   }

   public void setFlags(int var1) {
      this.flags = var1;
   }

   public int getFlags() {
      return this.flags;
   }
}
