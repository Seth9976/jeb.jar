package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerStaticOk;
import java.io.File;
import java.io.IOException;

@Ser
public class Version implements Comparable {
   public static final int RELEASE = 0;
   public static final int BETA = 1;
   public static final int ALPHA = 2;
   public static final int CHANNEL_STABLE = 0;
   public static final int CHANNEL_MOST_UNSTABLE = 2;
   @SerStaticOk
   private static final String[] channelNames = new String[]{"RELEASE", "BETA", "ALPHA"};
   @SerStaticOk
   private static final String[] channelInfoStrings = new String[]{
      S.L("Stable build."), S.L("Unstable build, may contain glitches."), S.L("Early unstable build, contains glitches and may crash.")
   };
   @SerId(1)
   private int major;
   @SerId(2)
   private int minor;
   @SerId(3)
   private int buildid;
   @SerId(4)
   private long timestamp;
   @SerId(5)
   private int channel;

   public static String getChannelName(int var0) {
      if (var0 < 0) {
         return "UNKNOWN";
      } else {
         switch (var0) {
            case 0:
            case 1:
            case 2:
               return channelNames[var0];
            default:
               return "UNSTABLE" + var0;
         }
      }
   }

   public static String getChannelInfo(int var0) {
      if (var0 < 0) {
         return S.L("UNKNOWN");
      } else {
         switch (var0) {
            case 0:
            case 1:
            case 2:
               return channelInfoStrings[var0];
            default:
               return S.L("Illegal build channel, certainly unstable.");
         }
      }
   }

   public static String[] getChannelNames() {
      return channelNames;
   }

   public Version(Version var1) {
      this(var1.major, var1.minor, var1.buildid, var1.timestamp, var1.channel);
   }

   public Version(int var1, int var2) {
      this(var1, var2, 0, 0L);
   }

   public Version(int var1, int var2, int var3) {
      this(var1, var2, var3, 0L);
   }

   public Version(int var1, int var2, int var3, long var4) {
      this(var1, var2, var3, var4, 0);
   }

   public Version(int var1, int var2, int var3, long var4, int var6) {
      if (var1 >= 0 && var2 >= 0 && var3 >= 0 && var4 >= 0L && var6 >= 0 && var6 <= 2) {
         this.major = var1;
         this.minor = var2;
         this.buildid = var3;
         this.timestamp = var4;
         this.channel = var6;
      } else {
         throw new RuntimeException("Invalid version");
      }
   }

   public static Version create(int var0, int var1) {
      return new Version(var0, var1, 0, 0L, 0);
   }

   public static Version create(int var0, int var1, int var2) {
      return new Version(var0, var1, var2, 0L, 0);
   }

   public static Version create(int var0, int var1, int var2, long var3) {
      return new Version(var0, var1, var2, var3);
   }

   public static Version create(int var0, int var1, int var2, long var3, int var5) {
      return new Version(var0, var1, var2, var3, var5);
   }

   public int getMajor() {
      return this.major;
   }

   public int getMinor() {
      return this.minor;
   }

   public int getBuildid() {
      return this.buildid;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public int getChannel() {
      return this.channel;
   }

   public String getChannelString() {
      switch (this.channel) {
         case 0:
            return null;
         case 1:
            return "beta";
         case 2:
            return "alpha";
         default:
            throw new RuntimeException("Invalid channel id");
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.buildid;
      var1 = 31 * var1 + this.major;
      var1 = 31 * var1 + this.minor;
      var1 = 31 * var1 + Long.valueOf(this.timestamp).hashCode();
      return 31 * var1 + this.channel;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Version var2 = (Version)var1;
         return this.major == var2.major
            && this.minor == var2.minor
            && this.buildid == var2.buildid
            && this.timestamp == var2.timestamp
            && this.channel == var2.channel;
      } else {
         return false;
      }
   }

   public int compareTo(Version var1) {
      if (this.major > var1.major) {
         return 1;
      } else if (this.major == var1.major && this.minor > var1.minor) {
         return 1;
      } else if (this.major == var1.major && this.minor == var1.minor && this.buildid > var1.buildid) {
         return 1;
      } else if (this.major == var1.major && this.minor == var1.minor && this.buildid == var1.buildid && this.timestamp > var1.timestamp) {
         return 1;
      } else {
         return this.major == var1.major && this.minor == var1.minor && this.buildid == var1.buildid && this.timestamp == var1.timestamp
            ? -Integer.compare(this.channel, var1.channel)
            : -1;
      }
   }

   public int compareToIgnoreTimestamp(Version var1) {
      if (this.major > var1.major) {
         return 1;
      } else if (this.major == var1.major && this.minor > var1.minor) {
         return 1;
      } else if (this.major == var1.major && this.minor == var1.minor && this.buildid > var1.buildid) {
         return 1;
      } else {
         return this.major == var1.major && this.minor == var1.minor && this.buildid == var1.buildid ? -Integer.compare(this.channel, var1.channel) : -1;
      }
   }

   public int compareToIgnoreChannel(Version var1) {
      if (this.major > var1.major) {
         return 1;
      } else if (this.major == var1.major && this.minor > var1.minor) {
         return 1;
      } else if (this.major == var1.major && this.minor == var1.minor && this.buildid > var1.buildid) {
         return 1;
      } else {
         return this.major == var1.major && this.minor == var1.minor && this.buildid == var1.buildid ? Long.compare(this.timestamp, var1.timestamp) : -1;
      }
   }

   public int compareToIgnoreTimestampAndChannel(Version var1) {
      if (this.major > var1.major) {
         return 1;
      } else if (this.major == var1.major && this.minor > var1.minor) {
         return 1;
      } else {
         return this.major == var1.major && this.minor == var1.minor ? Integer.compare(this.buildid, var1.buildid) : -1;
      }
   }

   public boolean like(int var1) {
      return this.major == var1;
   }

   public boolean like(int var1, int var2) {
      return this.major == var1 && this.minor == var2;
   }

   public boolean like(int var1, int var2, int var3) {
      return this.major == var1 && this.minor == var2 && this.buildid == var3;
   }

   public String format(boolean var1) {
      if (this.channel != 0) {
         if (var1) {
            String var2 = this.getChannelString();
            return this.timestamp == 0L
               ? Strings.ff("%d.%d-%s.%d", this.major, this.minor, var2, this.buildid)
               : Strings.ff("%d.%d-%s.%d.%d", this.major, this.minor, var2, this.buildid, this.timestamp);
         } else {
            return Strings.ff("%d.%d.%d.%d.%d", this.major, this.minor, this.buildid, this.timestamp, this.channel);
         }
      } else {
         return this.timestamp == 0L
            ? Strings.ff("%d.%d.%d", this.major, this.minor, this.buildid)
            : Strings.ff("%d.%d.%d.%d", this.major, this.minor, this.buildid, this.timestamp);
      }
   }

   public String formatCompact() {
      if (this.channel != 0) {
         return Strings.ff("%d.%d.%d.%d.%d", this.major, this.minor, this.buildid, this.timestamp, this.channel);
      } else if (this.timestamp != 0L) {
         return Strings.ff("%d.%d.%d.%d", this.major, this.minor, this.buildid, this.timestamp);
      } else {
         return this.buildid != 0 ? Strings.ff("%d.%d.%d", this.major, this.minor, this.buildid) : Strings.ff("%d.%d", this.major, this.minor);
      }
   }

   @Override
   public String toString() {
      return this.format(true);
   }

   public static Version parseFromString(String var0) {
      if (var0 != null && var0.length() != 0) {
         String[] var1 = var0.trim().split("\\.", -1);
         if (var1.length > 5) {
            return null;
         } else {
            boolean var2 = var1.length != 5;

            try {
               int var3 = Integer.parseInt(var1[0]);
               int var4 = 0;
               int var5 = 0;
               if (var2) {
                  if (var1.length >= 2) {
                     String var6 = var1[1];
                     int var7 = var6.indexOf(45);
                     if (var7 < 0) {
                        var4 = Integer.parseInt(var6);
                        var5 = 0;
                     } else {
                        var4 = Integer.parseInt(var6.substring(0, var7));
                        String var8 = var6.substring(var7 + 1);
                        switch (var8) {
                           case "beta":
                              var5 = 1;
                              break;
                           case "alpha":
                              var5 = 2;
                              break;
                           default:
                              throw new RuntimeException();
                        }
                     }
                  }
               } else {
                  var4 = Integer.parseInt(var1[1]);
                  var5 = Integer.parseInt(var1[4]);
               }

               int var13 = 0;
               if (var1.length >= 3) {
                  var13 = Integer.parseInt(var1[2]);
               }

               long var14 = 0L;

               try {
                  if (var1.length >= 4) {
                     var14 = Long.parseLong(var1[3]);
                  }
               } catch (Exception var11) {
               }

               return new Version(var3, var4, var13, var14, var5);
            } catch (Exception var12) {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   public static Version parseFromFile(File var0) {
      try {
         String var1 = new String(IO.readFile(var0), "UTF-8");
         return parseFromString(var1.trim());
      } catch (IOException var2) {
         return null;
      }
   }

   public int toInt() {
      int var1 = Math.min(this.major, 255);
      int var2 = Math.min(this.minor, 255);
      int var3 = Math.min(this.buildid, 255);
      return var1 * 65536 + var2 * 256 + var3;
   }

   public static Version fromInt(int var0) {
      int var1 = var0 >> 16 & 0xFF;
      int var2 = var0 >> 8 & 0xFF;
      int var3 = var0 & 0xFF;
      return new Version(var1, var2, var3);
   }
}
