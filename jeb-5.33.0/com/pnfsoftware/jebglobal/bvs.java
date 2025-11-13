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

public class bvs extends URLClassLoader {
   private static final ILogger ys = GlobalLog.getLogger(bvs.class);
   private static Set ld = new TreeSet();
   private btp gp;
   private Map oT = new HashMap();
   public Set pC = new ConcurrentHashSet();
   public Class A;
   public Class kS;
   public Class wS;
   public Class UT;
   public Class E;
   public Class sY;
   private static HashMap fI = new HashMap();

   public bvs(btp var1) {
      super(new URL[0], ClassLoader.getSystemClassLoader());
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.gp = var1;

         try {
            this.A = this.loadClass(ckx.pC(new byte[]{41, 14, 6, 24, 92, 5, 6, 6, 19, 14, 107, 15, 72, 83, 66}, 2, 91));
            this.kS = this.loadClass(ckx.pC(new byte[]{41, 14, 6, 24, 92, 5, 6, 6, 19, 14, 103, 1, 67, 69, 82, 77}, 2, 76));
            this.wS = this.loadClass(ckx.pC(new byte[]{-22, 11, 23, 23, 79, 66, 13, 15, 9, 73, 125, 39, 6, 27, 7, 9}, 1, 128));
            this.UT = this.loadClass(ckx.pC(new byte[]{97, 11, 23, 23, 79, 66, 13, 15, 9, 73, 122, 60, 26, 29, 24, 22, 3, 14, 9}, 1, 11));
            this.E = this.loadClass(
               ckx.pC(new byte[]{11, 11, 23, 23, 79, 66, 13, 15, 9, 73, 125, 39, 21, 2, 8, 63, 38, 19, 2, 6, 32, 41, 9, 8, 8, 11, 26}, 1, 97)
            );
            this.sY = this.loadClass(
               ckx.pC(
                  new byte[]{24, 35, 26, 24, 4, 8, 73, 4, 21, 78, 79, 77, 122, 84, 80, 90, 82, 103, 94, 65, 81, 85, 119, 89, 73, 77, 42, 28, 21, 88}, 2, 160
               )
            );
         } catch (ClassNotFoundException var3) {
            throw new RuntimeException(var3);
         }
      }
   }

   public void pC() {
      if (!this.A()) {
         try {
            super.close();
         } catch (IOException var2) {
            ys.catching(var2);
         }

         this.oT = null;
         this.A = null;
         this.kS = null;
         this.wS = null;
         this.UT = null;
         this.E = null;
         this.sY = null;
         this.gp = null;
      }
   }

   public boolean A() {
      return this.gp == null;
   }

   private void wS() {
      if (this.A()) {
         throw new IllegalStateException(ckx.pC(new byte[]{-61, 60, 13, 69, 115, 16, 15, 108, 73, 26, 83, 68, 13, 26, 3, 31, 28, 22, 1, 69}, 1, 151));
      }
   }

   public int kS() {
      return this.pC.size();
   }

   public void pC(File var1) throws IOException {
      this.wS();
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (var1.isFile() && var1.canRead()) {
         URL var2 = var1.toURI().toURL();
         this.addURL(var2);
      } else {
         throw new IOException(
            ckx.pC(new byte[]{14, 34, 15, 0, 1, 27, 84, 65, 2, 0, 6, 22, 0, 83, 79, 29, 82, 82, 23, 4, 5, 68, 70, 15, 5, 9, 95, 26}, 1, 77) + var1
         );
      }
   }

   public Class pC(String var1) {
      this.wS();
      return (Class)this.oT.get(var1);
   }

   public Class A(String var1) throws ClassNotFoundException {
      this.wS();
      Class var2 = (Class)this.oT.get(var1);
      if (var2 == null) {
         String var3 = kS(var1);
         var2 = this.loadClass(var3);
         this.oT.put(var1, var2);
      }

      return var2;
   }

   @Override
   public Class loadClass(String var1) throws ClassNotFoundException {
      var1 = var1.replace('/', '.');
      Class var2 = this.wS(var1);
      this.pC.add(var1);
      return var2;
   }

   private Class wS(String var1) throws ClassNotFoundException {
      this.wS();
      if (var1.startsWith("[")) {
         return Class.forName(var1, true, this);
      } else {
         String var2 = (String)fI.get(var1);
         if (var2 != null) {
            Class var9 = super.loadClass(var2);

            try {
               return (Class)var9.getField("TYPE").get(null);
            } catch (Exception var5) {
               throw new ClassNotFoundException(
                  Strings.ff(
                     ckx.pC(
                        new byte[]{
                           104,
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
                        43
                     ),
                     var2
                  ),
                  var5
               );
            }
         } else {
            if (this.gp.NS().pC()) {
               Class var3;
               try {
                  var3 = this.gp.NS().loadClass(var1);
               } catch (DexDecEvalSandboxExecutionException var6) {
                  throw new ClassNotFoundException(
                     ckx.pC(new byte[]{-71, 97, 72, 7, 0, 4, 75, 82, 23, 3, 19, 6, 22, 1, 68, 84, 27, 79, 76, 3, 14, 5, 68, 67, 15, 13, 18, 0, 73, 26}, 1, 248)
                        + var1,
                     var6
                  );
               }

               if (var3 != null) {
                  return var3;
               }
            }

            for (String var4 : ld) {
               if (var1.startsWith(var4) || "*".equals(var4)) {
                  try {
                     return super.loadClass(var1);
                  } catch (Throwable var7) {
                     break;
                  }
               }
            }

            throw new ClassNotFoundException(
               ckx.pC(
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
                        90,
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
                     175
                  )
                  + var1
            );
         }
      }
   }

   @Override
   public String toString() {
      return ckx.pC(new byte[]{0, 3, 17, 10, 1, 37, 8, 9, 16, 69, 90, 35, 27, 16, 1, 8, 102}, 2, 149) + Thread.currentThread().getId();
   }

   public static String kS(String var0) {
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

   public static String pC(Class var0) {
      return JvmUtil.generateTypeSig(var0);
   }

   static {
      ld.add(ckx.pC(new byte[]{15, 11, 23, 23, 79}, 1, 101));
      ld.add(ckx.pC(new byte[]{18, 11, 23, 23, 25, 86}, 1, 120));
      ld.add(ckx.pC(new byte[]{3, 15, 10, 22, 29, 6, 13, 74}, 1, 98));
      ld.add(ckx.pC(new byte[]{113, 12, 2, 67, 79, 15, 10, 22, 29, 6, 13, 74}, 1, 18));
      ld.add(ckx.pC(new byte[]{39, 14, 28, 15, 27, 2, 73}, 2, 190));
      ld.add(ckx.pC(new byte[]{-33, 5, 11, 1, 12, 29, 23, 75}, 1, 179));
      ld.add(ckx.pC(new byte[]{44, 29, 23, 87}, 2, 129));
      ld.add(ckx.pC(new byte[]{-43, 6, 27, 64}, 1, 166));
      ld.add(ckx.pC(new byte[]{45, 14, 15, 69, 71, 7, 26, 17, 23, 28, 15, 13, 66}, 1, 71));
      ld.add(bvo.oT);
      ld.add(ckx.pC(new byte[]{32, 0, 29, 87, 2, 7, 1, 27, 27, 70, 92, 20, 72, 82, 84, 23, 83, 86, 78, 14}, 2, 55));
      ld.add(ckx.pC(new byte[]{-51, 12, 2, 67, 94, 30, 8, 21, 28, 9, 18, 3, 22, 19, 23, 75, 68, 15, 7, 5, 11, 3, 13, 3, 13, 66}, 1, 174));
      fI.put("Z", "java.lang.Boolean");
      fI.put("B", "java.lang.Byte");
      fI.put("C", "java.lang.Character");
      fI.put("S", "java.lang.Short");
      fI.put("I", "java.lang.Integer");
      fI.put("J", "java.lang.Long");
      fI.put("F", "java.lang.Float");
      fI.put("D", "java.lang.Double");
      fI.put("V", "java.lang.Void");
   }
}
