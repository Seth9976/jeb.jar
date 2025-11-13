package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalSandboxExecutionException;
import com.pnfsoftware.jeb.util.collect.ConcurrentHashSet;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class cah extends URLClassLoader {
   private static final ILogger nf = GlobalLog.getLogger(cah.class);
   private static Set gP = new TreeSet();
   private bye za;
   private Map lm = new HashMap();
   public Set q = new ConcurrentHashSet();
   public Class RF;
   public Class xK;
   public Class Dw;
   public Class Uv;
   public Class oW;
   public Class gO;
   private static HashMap zz = new HashMap();

   public cah(bye var1) {
      super(new URL[0], ClassLoader.getSystemClassLoader());
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.za = var1;

         try {
            this.RF = this.loadClass(cvm.q(new byte[]{41, 14, 6, 24, 92, 5, 6, 6, 19, 14, 107, 15, 72, 83, 66}, 2, 175));
            this.xK = this.loadClass(cvm.q(new byte[]{-13, 11, 23, 23, 79, 66, 13, 15, 9, 73, 97, 45, 8, 15, 6, 23}, 1, 153));
            this.Dw = this.loadClass(cvm.q(new byte[]{-128, 11, 23, 23, 79, 66, 13, 15, 9, 73, 125, 39, 6, 27, 7, 9}, 1, 234));
            this.Uv = this.loadClass(cvm.q(new byte[]{41, 14, 6, 24, 92, 5, 6, 6, 19, 14, 124, 11, 91, 79, 70, 88, 91, 95, 73}, 2, 183));
            this.oW = this.loadClass(
               cvm.q(new byte[]{41, 14, 6, 24, 92, 5, 6, 6, 19, 14, 123, 23, 72, 67, 90, 109, 75, 82, 79, 69, 119, 92, 87, 89, 73, 78, 59}, 2, 216)
            );
            this.gO = this.loadClass(
               cvm.q(new byte[]{38, 23, 38, 11, 23, 23, 79, 66, 13, 15, 9, 73, 125, 39, 21, 2, 8, 63, 38, 19, 2, 6, 32, 41, 9, 8, 8, 11, 26, 79}, 1, 125)
            );
         } catch (ClassNotFoundException var3) {
            throw new RuntimeException(var3);
         }
      }
   }

   public void q() {
      if (!this.RF()) {
         try {
            super.close();
         } catch (IOException var2) {
            nf.catching(var2);
         }

         this.lm = null;
         this.RF = null;
         this.xK = null;
         this.Dw = null;
         this.Uv = null;
         this.oW = null;
         this.gO = null;
         this.za = null;
      }
   }

   public boolean RF() {
      return this.za == null;
   }

   private void Dw() {
      if (this.RF()) {
         throw new IllegalStateException(cvm.q(new byte[]{-74, 60, 13, 69, 115, 16, 15, 108, 73, 26, 83, 68, 13, 26, 3, 31, 28, 22, 1, 69}, 1, 226));
      }
   }

   public int xK() {
      return this.q.size();
   }

   public void q(File var1) throws IOException {
      this.Dw();
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (var1.isFile() && var1.canRead()) {
         URL var2 = var1.toURI().toURL();
         this.addURL(var2);
      } else {
         throw new IOException(
            cvm.q(new byte[]{0, 14, 30, 23, 29, 29, 71, 9, 23, 67, 77, 16, 90, 0, 94, 75, 25, 65, 73, 65, 86, 16, 84, 93, 64, 69, 117, 82}, 2, 210) + var1
         );
      }
   }

   public Class q(String var1) {
      this.Dw();
      return (Class)this.lm.get(var1);
   }

   public Class RF(String var1) throws ClassNotFoundException {
      this.Dw();
      Class var2 = (Class)this.lm.get(var1);
      if (var2 == null) {
         String var3 = xK(var1);
         var2 = this.loadClass(var3);
         this.lm.put(var1, var2);
      }

      return var2;
   }

   @Override
   public Class loadClass(String var1) throws ClassNotFoundException {
      Class var2 = this.Dw(var1);
      this.q.add(var1);
      return var2;
   }

   private Class Dw(String var1) throws ClassNotFoundException {
      this.Dw();
      if (var1.startsWith("[")) {
         return Class.forName(var1, true, this);
      } else {
         String var2 = (String)zz.get(var1);
         if (var2 != null) {
            Class var9 = super.loadClass(var2);

            try {
               return (Class)var9.getField("TYPE").get(null);
            } catch (Exception var5) {
               throw new ClassNotFoundException(
                  Strings.ff(
                     cvm.q(
                        new byte[]{
                           45,
                           34,
                           15,
                           0,
                           1,
                           27,
                           84,
                           82,
                           23,
                           17,
                           6,
                           27,
                           12,
                           19,
                           19,
                           69,
                           99,
                           47,
                           13,
                           18,
                           0,
                           83,
                           79,
                           13,
                           8,
                           15,
                           6,
                           23,
                           84,
                           5,
                           86,
                           93,
                           122,
                           13,
                           9,
                           21
                        },
                        1,
                        110
                     ),
                     var2
                  ),
                  var5
               );
            }
         } else {
            if (this.za.HF().q()) {
               Class var3;
               try {
                  var3 = this.za.HF().loadClass(var1);
               } catch (DexDecEvalSandboxExecutionException var6) {
                  throw new ClassNotFoundException(
                     cvm.q(
                           new byte[]{2, 79, 24, 22, 29, 2, 71, 26, 17, 70, 93, 16, 76, 68, 17, 77, 86, 19, 64, 79, 83, 84, 18, 87, 64, 65, 60, 1, 91, 67},
                           2,
                           230
                        )
                        + var1,
                     var6
                  );
               }

               if (var3 != null) {
                  return var3;
               }
            }

            for (String var4 : gP) {
               if (var1.startsWith(var4) || "*".equals(var4)) {
                  try {
                     return super.loadClass(var1);
                  } catch (Throwable var7) {
                     break;
                  }
               }
            }

            throw new ClassNotFoundException(
               cvm.q(
                     new byte[]{
                        17,
                        10,
                        22,
                        12,
                        1,
                        0,
                        9,
                        15,
                        84,
                        84,
                        71,
                        67,
                        69,
                        79,
                        80,
                        93,
                        25,
                        80,
                        64,
                        65,
                        65,
                        67,
                        18,
                        91,
                        94,
                        0,
                        44,
                        30,
                        0,
                        16,
                        31,
                        69,
                        78,
                        14,
                        26,
                        68,
                        73,
                        0,
                        7,
                        78,
                        13,
                        78,
                        83
                     },
                     2,
                     244
                  )
                  + var1
            );
         }
      }
   }

   @Override
   public String toString() {
      return cvm.q(new byte[]{13, 47, 13, 18, 0, 63, 35, 14, 5, 1, 23, 50, 114, 2, 0, 1, 110}, 1, 78) + Thread.currentThread().getId();
   }

   public static String xK(String var0) {
      int var1 = 0;
      char var2 = var0.charAt(var1);

      while (var2 == '[') {
         var2 = var0.charAt(++var1);
      }

      if (var2 != 'L') {
         return var0;
      } else {
         return var1 == 0 ? var0.substring(1, var0.length() - 1).replace('/', '.') : var0.substring(0, var1) + var0.substring(var1).replace('/', '.');
      }
   }

   public static String q(Class var0) {
      return JvmUtil.generateTypeSig(var0);
   }

   static {
      gP.add(cvm.q(new byte[]{41, 14, 6, 24, 92}, 2, 60));
      gP.add(cvm.q(new byte[]{108, 11, 23, 23, 25, 86}, 1, 6));
      gP.add(cvm.q(new byte[]{34, 1, 20, 11, 29, 0, 3, 70}, 2, 194));
      gP.add(cvm.q(new byte[]{32, 0, 29, 87, 19, 7, 3, 26, 27, 73, 76, 77}, 2, 66));
      gP.add(cvm.q(new byte[]{39, 14, 28, 15, 27, 2, 73}, 2, 18));
      gP.add(cvm.q(new byte[]{47, 6, 18, 26, 29, 27, 2, 70}, 2, 113));
      gP.add(cvm.q(new byte[]{44, 29, 23, 87}, 2, 123));
      gP.add(cvm.q(new byte[]{48, 26, 30, 87}, 2, 233));
      gP.add(cvm.q(new byte[]{41, 11, 27, 87, 27, 7, 19, 13, 6, 78, 73, 15, 7}, 2, 157));
      gP.add(cad.lm);
      gP.add(cvm.q(new byte[]{32, 0, 29, 87, 2, 7, 1, 27, 27, 70, 92, 20, 72, 82, 84, 23, 83, 86, 78, 14}, 2, 114));
      gP.add(cvm.q(new byte[]{32, 0, 29, 87, 2, 7, 1, 27, 27, 70, 92, 20, 72, 82, 84, 23, 83, 86, 78, 71, 94, 95, 80, 85, 64, 14}, 2, 89));
      zz.put("Z", "java.lang.Boolean");
      zz.put("B", "java.lang.Byte");
      zz.put("C", "java.lang.Character");
      zz.put("S", "java.lang.Short");
      zz.put("I", "java.lang.Integer");
      zz.put("J", "java.lang.Long");
      zz.put("F", "java.lang.Float");
      zz.put("D", "java.lang.Double");
      zz.put("V", "java.lang.Void");
   }
}
