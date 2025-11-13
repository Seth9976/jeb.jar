package com.pnfsoftware.jeb.util.format;

import com.pnfsoftware.jeb.util.base.SystemUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.lang.reflect.Field;

public class XercesUpdater {
   private static final ILogger logger = GlobalLog.getLogger(XercesUpdater.class);
   private static final int majorJavaVersion = SystemUtil.getMajorJavaVersion();
   private ClassLoader cl;
   private int modcnt;
   private byte[] array;

   public XercesUpdater() {
      this(ClassLoader.getSystemClassLoader());
   }

   public XercesUpdater(ClassLoader var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.cl = var1;
      }
   }

   public int getModificationCount() {
      return this.modcnt;
   }

   public int update() {
      return this.updateXML10();
   }

   private int updateXML10() {
      XercesUpdater.E[] var1 = new XercesUpdater.E[]{
         new XercesUpdater.E(0, "com.sun.org.apache.xerces.internal.util.XMLChar", "CHARS"),
         new XercesUpdater.E(0, "com.sun.org.apache.xml.internal.utils.XMLChar", "CHARS"),
         new XercesUpdater.E(0, "org.apache.xerces.util.XMLChar", "CHARS"),
         new XercesUpdater.E(0, "com.sun.xml.internal.fastinfoset.org.apache.xerces.util.XMLChar", "CHARS")
      };
      int var2 = 0;

      for (XercesUpdater.E var6 : var1) {
         if (this.updateXML10(var6)) {
            var2++;
         }
      }

      return var2;
   }

   private boolean updateXML10(XercesUpdater.E var1) {
      if (var1.maxJavaVer != 0 && var1.maxJavaVer < majorJavaVersion) {
         return false;
      } else {
         try {
            Class var2 = Class.forName(var1.cname, true, this.cl);
            Field var3 = var2.getDeclaredField(var1.fname);
            var3.setAccessible(true);

            try {
               this.array = (byte[])var3.get(null);
               if (this.array.length == 65536) {
                  this.setNameStart(192, 214);
                  this.setNameStart(216, 246);
                  this.setNameStart(248, 767);
                  this.setNameStart(880, 893);
                  this.setNameStart(895, 8191);
                  this.setNameStart(8204, 8205);
                  this.setNameStart(8304, 8591);
                  this.setNameStart(11264, 12271);
                  this.setNameStart(12289, 55295);
                  this.setNameStart(63744, 64975);
                  this.setNameStart(65008, 65533);
                  this.setName(768, 879);
                  this.setName(8255, 8256);
               }
            } finally {
               var3.setAccessible(false);
            }

            return true;
         } catch (Exception var8) {
            return false;
         }
      }
   }

   private void setNameStart(int var1, int var2) {
      for (int var3 = var1; var3 <= var2; var3++) {
         if ((this.array[var3] & 15) != 13) {
            this.array[var3] = (byte)(this.array[var3] & 240 | 13);
            this.modcnt++;
         }
      }
   }

   private void setName(int var1, int var2) {
      for (int var3 = var1; var3 <= var2; var3++) {
         if ((this.array[var3] & 15) != 9) {
            this.array[var3] = (byte)(this.array[var3] & 240 | 9);
            this.modcnt++;
         }
      }
   }

   static class E {
      int maxJavaVer;
      String cname;
      String fname;

      public E(int var1, String var2, String var3) {
         this.maxJavaVer = var1;
         this.cname = var2;
         this.fname = var3;
      }
   }
}
