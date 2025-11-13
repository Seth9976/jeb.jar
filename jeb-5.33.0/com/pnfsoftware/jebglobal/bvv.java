package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.lang.reflect.ReflectPermission;
import java.security.Permission;
import java.security.Permissions;
import java.security.SecurityPermission;
import java.util.PropertyPermission;
import java.util.logging.LoggingPermission;

class bvv extends SecurityManager {
   private static final ILogger A = GlobalLog.getLogger(bvv.class);
   private static final bte kS = new bte();
   private static final String wS = ckx.pC(new byte[]{49, 10, 17, 29}, 2, 14);
   private static final String UT = ckx.pC(new byte[]{63, 5, 27, 29, 17}, 1, 72);
   private static final String E = ckx.pC(new byte[]{106, 1, 9, 9, 17, 17}, 1, 14);
   private InheritableThreadLocal sY;
   private Permissions ys;
   File pC;

   bvv() {
      this(false);
   }

   bvv(boolean var1) {
      this.sY = new bvv.Av(var1);
      this.ys = new Permissions();
      this.ys.add(new RuntimePermission(ckx.pC(new byte[]{36, 10, 4, 41, 0, 6, 19, 13, 23, 84, 65, 12, 71, 100, 94, 84, 88, 90, 66}, 2, 80)));
      this.ys.add(new RuntimePermission(ckx.pC(new byte[]{36, 10, 4, 58, 30, 8, 20, 27, 56, 79, 73, 7, 76, 82}, 2, 125)));
      this.ys.add(new RuntimePermission(ckx.pC(new byte[]{34, 12, 19, 28, 1, 26, 35, 13, 23, 76, 73, 17, 76, 68, 124, 92, 84, 81, 73, 82, 65}, 2, 29)));
      this.ys.add(new RuntimePermission(ckx.pC(new byte[]{36, 10, 4, 42, 6, 8, 4, 3, 32, 82, 73, 0, 76}, 2, 103)));
      this.ys.add(new RuntimePermission(ckx.pC(new byte[]{-118, 2, 17, 55, 47, 13, 18, 0, 63, 35, 14, 5, 1, 23}, 1, 237)));
      this.ys.add(new RuntimePermission(ckx.pC(new byte[]{32, 7, 17, 11, 1, 12, 19, 56, 6, 79, 94, 10, 77, 69, 67}, 2, 54)));
      this.ys
         .add(
            new RuntimePermission(
               ckx.pC(
                  new byte[]{
                     45, 10, 4, 87, 16, 16, 19, 13, 22, 85, 76, 7, 80, 14, 82, 75, 92, 82, 88, 69, 120, 81, 68, 84, 104, 73, 60, 2, 0, 23, 15, 13, 69, 19
                  },
                  2,
                  162
               )
            )
         );
      this.ys
         .add(
            new ReflectPermission(
               ckx.pC(
                  new byte[]{
                     -122,
                     11,
                     18,
                     39,
                     34,
                     29,
                     23,
                     1,
                     48,
                     39,
                     62,
                     49,
                     2,
                     8,
                     10,
                     6,
                     2,
                     75,
                     64,
                     11,
                     17,
                     90,
                     76,
                     27,
                     13,
                     17,
                     7,
                     23,
                     17,
                     0,
                     29,
                     87,
                     91,
                     1,
                     29,
                     5,
                     5,
                     29,
                     13
                  },
                  1,
                  232
               )
            )
         );
      this.ys
         .add(
            new ReflectPermission(
               ckx.pC(
                  new byte[]{
                     45,
                     10,
                     7,
                     41,
                     0,
                     6,
                     31,
                     17,
                     61,
                     78,
                     120,
                     2,
                     74,
                     75,
                     80,
                     94,
                     92,
                     29,
                     66,
                     69,
                     70,
                     30,
                     80,
                     76,
                     88,
                     69,
                     45,
                     7,
                     5,
                     7,
                     21,
                     75,
                     68,
                     4,
                     29,
                     7,
                     93,
                     6,
                     2,
                     84,
                     0,
                     27,
                     29
                  },
                  2,
                  155
               )
            )
         );
      this.ys
         .add(
            new ReflectPermission(
               ckx.pC(
                  new byte[]{
                     45,
                     10,
                     7,
                     41,
                     0,
                     6,
                     31,
                     17,
                     61,
                     78,
                     120,
                     2,
                     74,
                     75,
                     80,
                     94,
                     92,
                     29,
                     66,
                     69,
                     70,
                     30,
                     80,
                     76,
                     88,
                     69,
                     45,
                     7,
                     5,
                     7,
                     21,
                     75,
                     68,
                     4,
                     29,
                     7,
                     93,
                     6,
                     2,
                     84,
                     0,
                     27,
                     29,
                     14,
                     0,
                     8,
                     8,
                     6,
                     24,
                     8,
                     21,
                     29,
                     10,
                     29
                  },
                  2,
                  118
               )
            )
         );
      this.ys
         .add(
            new ReflectPermission(
               ckx.pC(
                  new byte[]{
                     -110,
                     11,
                     18,
                     39,
                     34,
                     29,
                     23,
                     1,
                     48,
                     39,
                     62,
                     49,
                     2,
                     8,
                     10,
                     6,
                     2,
                     75,
                     64,
                     11,
                     17,
                     90,
                     76,
                     27,
                     13,
                     17,
                     7,
                     23,
                     17,
                     0,
                     29,
                     87,
                     74,
                     1,
                     22,
                     16,
                     17,
                     27,
                     25,
                     4,
                     29,
                     6,
                     1,
                     64,
                     75,
                     11,
                     27,
                     24,
                     8,
                     23,
                     19,
                     21,
                     29,
                     6,
                     1
                  },
                  1,
                  252
               )
            )
         );
      this.ys
         .add(
            new ReflectPermission(
               ckx.pC(
                  new byte[]{
                     -71,
                     11,
                     18,
                     39,
                     34,
                     29,
                     23,
                     1,
                     48,
                     39,
                     62,
                     49,
                     2,
                     8,
                     10,
                     6,
                     2,
                     75,
                     64,
                     11,
                     17,
                     90,
                     76,
                     27,
                     13,
                     17,
                     7,
                     23,
                     17,
                     0,
                     29,
                     87,
                     74,
                     1,
                     22,
                     16,
                     17,
                     27,
                     25,
                     4,
                     29,
                     6,
                     1,
                     64,
                     72,
                     15,
                     12,
                     9,
                     8
                  },
                  1,
                  215
               )
            )
         );
      this.ys
         .add(
            new ReflectPermission(
               ckx.pC(
                  new byte[]{
                     -119,
                     11,
                     18,
                     39,
                     34,
                     29,
                     23,
                     1,
                     48,
                     39,
                     62,
                     49,
                     2,
                     8,
                     10,
                     6,
                     2,
                     75,
                     64,
                     11,
                     17,
                     90,
                     76,
                     27,
                     13,
                     17,
                     7,
                     23,
                     17,
                     0,
                     29,
                     87,
                     74,
                     1,
                     22,
                     16,
                     17,
                     27,
                     25,
                     4,
                     29,
                     6,
                     1,
                     64,
                     67,
                     8,
                     17,
                     28,
                     7,
                     11
                  },
                  1,
                  231
               )
            )
         );
      this.ys
         .add(
            new ReflectPermission(
               ckx.pC(
                  new byte[]{
                     -64,
                     11,
                     18,
                     39,
                     34,
                     29,
                     23,
                     1,
                     48,
                     39,
                     62,
                     49,
                     2,
                     8,
                     10,
                     6,
                     2,
                     75,
                     64,
                     11,
                     17,
                     90,
                     76,
                     27,
                     13,
                     17,
                     7,
                     23,
                     17,
                     0,
                     29,
                     87,
                     74,
                     1,
                     22,
                     16,
                     17,
                     27,
                     25,
                     4,
                     29,
                     6,
                     1,
                     64,
                     67,
                     2,
                     11,
                     13,
                     15,
                     15,
                     12,
                     23
                  },
                  1,
                  174
               )
            )
         );
      this.ys
         .add(
            new ReflectPermission(
               ckx.pC(
                  new byte[]{
                     45,
                     10,
                     7,
                     41,
                     0,
                     6,
                     31,
                     17,
                     61,
                     78,
                     120,
                     2,
                     74,
                     75,
                     80,
                     94,
                     92,
                     29,
                     66,
                     69,
                     70,
                     30,
                     80,
                     76,
                     88,
                     69,
                     45,
                     7,
                     5,
                     7,
                     21,
                     75,
                     68,
                     4,
                     29,
                     7,
                     93,
                     6,
                     2,
                     84,
                     0,
                     27,
                     29,
                     14,
                     21,
                     31,
                     22,
                     12
                  },
                  2,
                  7
               )
            )
         );
      this.ys
         .add(
            new ReflectPermission(
               ckx.pC(
                  new byte[]{
                     45,
                     10,
                     7,
                     41,
                     0,
                     6,
                     31,
                     17,
                     61,
                     78,
                     120,
                     2,
                     74,
                     75,
                     80,
                     94,
                     92,
                     29,
                     66,
                     69,
                     70,
                     30,
                     80,
                     76,
                     88,
                     69,
                     45,
                     7,
                     5,
                     7,
                     21,
                     75,
                     68,
                     24,
                     0,
                     5,
                     66,
                     6,
                     17
                  },
                  2,
                  31
               )
            )
         );
      this.ys
         .add(
            new ReflectPermission(
               ckx.pC(
                  new byte[]{
                     45,
                     10,
                     7,
                     41,
                     0,
                     6,
                     31,
                     17,
                     61,
                     78,
                     120,
                     2,
                     74,
                     75,
                     80,
                     94,
                     92,
                     29,
                     66,
                     69,
                     70,
                     30,
                     80,
                     76,
                     88,
                     69,
                     45,
                     7,
                     5,
                     7,
                     21,
                     75,
                     68,
                     24,
                     0,
                     5,
                     66,
                     6,
                     17,
                     14,
                     5,
                     27,
                     18,
                     68,
                     8,
                     8,
                     1
                  },
                  2,
                  90
               )
            )
         );
      this.ys.add(new RuntimePermission(ckx.pC(new byte[]{14, 17, 23, 4, 21, 17, 38, 47, 13, 18, 0, 63, 35, 14, 5, 1, 23}, 1, 109)));
      this.ys.add(new ReflectPermission(ckx.pC(new byte[]{48, 26, 0, 9, 0, 12, 20, 27, 53, 67, 75, 6, 90, 83, 114, 81, 92, 80, 71, 83}, 2, 105)));
      this.ys.add(new RuntimePermission(ckx.pC(new byte[]{34, 12, 19, 28, 1, 26, 36, 4, 21, 83, 91, 42, 71, 112, 80, 90, 82, 82, 75, 69, 28, 26}, 2, 156)));
      this.ys.add(new FilePermission(ckx.pC(new byte[]{18, 0, 125, 13, 0, 108, 102, 15, 5, 9, 22, 109, 0}, 1, 46), wS));
      this.ys.add(new PropertyPermission("*", wS));
      this.ys.add(new RuntimePermission(ckx.pC(new byte[]{23, 3, 14, 5, 40, 37, 11, 16, 19, 19, 11, 87, 93, 6, 27, 11, 6}, 1, 123)));
      this.ys
         .add(
            new RuntimePermission(
               ckx.pC(new byte[]{47, 0, 17, 29, 62, 0, 5, 26, 21, 82, 81, 77, 90, 85, 95, 83, 90, 86, 115, 80, 64, 95, 68, 92, 72, 69, 61}, 2, 5)
            )
         );
      this.ys.add(new RuntimePermission(ckx.pC(new byte[]{47, 0, 17, 29, 62, 0, 5, 26, 21, 82, 81, 77, 90, 85, 95, 84, 74, 80, 77, 80, 91}, 2, 167)));
      this.ys.add(new RuntimePermission(ckx.pC(new byte[]{-74, 3, 14, 5, 40, 37, 11, 16, 19, 19, 11, 87, 93, 6, 27, 30, 27, 8, 16, 66, 0}, 1, 218)));
      this.ys.add(new RuntimePermission(ckx.pC(new byte[]{34, 12, 19, 28, 1, 26, 52, 17, 7, 84, 77, 14, 100, 79, 85, 76, 85, 86, 95}, 2, 57)));
      this.ys.add(new LoggingPermission(ckx.pC(new byte[]{-94, 12, 1, 26, 6, 29, 3}, 1, 193), ""));
      this.ys
         .add(
            new RuntimePermission(
               ckx.pC(new byte[]{75, 11, 15, 3, 14, 9, 38, 44, 1, 26, 17, 29, 12, 55, 47, 13, 18, 0, 63, 35, 14, 5, 1, 23, 61, 57, 19, 23, 0, 27, 13, 1}, 1, 46)
            )
         );
      this.ys.add(new RuntimePermission(ckx.pC(new byte[]{47, 0, 17, 29, 62, 0, 5, 26, 21, 82, 81, 77, 83, 73, 65}, 2, 104)));
      this.ys.add(new RuntimePermission(ckx.pC(new byte[]{47, 0, 19, 24, 30, 12, 52, 13, 6, 86, 65, 0, 76, 112, 67, 86, 79, 90, 72, 69, 64}, 2, 31)));
   }

   File pC(String var1, boolean var2) throws IOException {
      if (var1 == null) {
         throw new IllegalArgumentException(
            ckx.pC(
               new byte[]{
                  13,
                  26,
                  28,
                  21,
                  82,
                  5,
                  8,
                  11,
                  21,
                  84,
                  65,
                  12,
                  71,
                  0,
                  87,
                  86,
                  75,
                  19,
                  95,
                  65,
                  92,
                  84,
                  80,
                  90,
                  84,
                  0,
                  43,
                  0,
                  14,
                  19,
                  28,
                  0,
                  68,
                  65,
                  8,
                  13,
                  67,
                  10,
                  1
               },
               2,
               172
            )
         );
      } else if (this.pC != null) {
         return this.pC;
      } else {
         File var3 = new File(IO.getSessionTemporaryFolder(), ckx.pC(new byte[]{39, 10, 8, 29, 23, 10, 56, 27, 21, 78, 76, 1, 70, 88, 110, 95, 74}, 2, 230));
         var3.mkdir();
         this.pC = new File(var3, var1);
         if (!this.pC.getCanonicalPath().startsWith(var3.getCanonicalPath())) {
            throw new IOException();
         } else {
            this.pC.mkdir();
            this.pC(this.pC, true, var2);
            return this.pC;
         }
      }
   }

   File pC() {
      return this.pC;
   }

   private void pC(File var1, boolean var2, boolean var3) throws IOException {
      if (var1 != null && var1.isDirectory()) {
         String var4 = var1.getCanonicalPath();
         String var5 = var4 + File.separator + "-";
         if (var2) {
            this.ys.add(new FilePermission(var4, UT));
            this.ys.add(new FilePermission(var5, UT));
         }

         if (var3) {
            this.ys.add(new FilePermission(var4, E));
            this.ys.add(new FilePermission(var5, E));
         }
      } else {
         throw new IOException(ckx.pC(new byte[]{13, 0, 4, 89, 19, 73, 3, 1, 6, 69, 75, 23, 70, 82, 72, 3, 25}, 2, 155) + var1);
      }
   }

   private Boolean pC(Permission var1) {
      if (var1 instanceof SecurityPermission) {
         String var2 = var1.getName();
         if (var2.startsWith("getProperty.")) {
            return true;
         }

         if (var2.startsWith("putProviderProperty.")) {
            return true;
         }
      } else if (var1 instanceof RuntimePermission) {
         String var3 = var1.getName();
         if (var3.startsWith("exitVM.")) {
            return false;
         }
      }

      return null;
   }

   private boolean A(Permission var1) {
      return this.wS() || var1 instanceof bte;
   }

   public void A() {
      this.sY.set(true);
   }

   public void kS() {
      this.sY.set(false);
   }

   public boolean wS() {
      return (Boolean)this.sY.get();
   }

   @Override
   public void checkPermission(Permission var1) {
      if (this.A(var1)) {
         if (!this.ys.implies(var1)) {
            Boolean var2 = this.pC(var1);
            if (var2 != null) {
               if (!var2) {
                  throw new SecurityException();
               }
            } else {
               try {
                  super.checkPermission(var1);
               } catch (SecurityException var4) {
                  A.catchingSilent(var4);
                  throw var4;
               }
            }
         }
      }
   }

   @Override
   public void checkPermission(Permission var1, Object var2) {
      if (this.A(var1)) {
         if (!this.ys.implies(var1)) {
            Boolean var3 = this.pC(var1);
            if (var3 != null) {
               if (!var3) {
                  throw new SecurityException();
               }
            } else {
               try {
                  super.checkPermission(var1, var2);
               } catch (SecurityException var5) {
                  A.catchingSilent(var5);
                  throw var5;
               }
            }
         }
      }
   }

   private static class Av extends InheritableThreadLocal {
      boolean pC;

      Av(boolean var1) {
         this.pC = var1;
      }

      protected Boolean pC() {
         return this.pC;
      }

      public void pC(Boolean var1) {
         if (!var1) {
            SecurityManager var2 = System.getSecurityManager();
            if (var2 != null) {
               var2.checkPermission(bvv.kS);
            }
         }

         super.set(var1);
      }
   }
}
