package com.pnfsoftware.jebglobal;

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

class cak extends SecurityManager {
   private static final ILogger RF = GlobalLog.getLogger(cak.class);
   private static final bxt xK = new bxt();
   private static final String Dw = cvm.q(new byte[]{49, 10, 17, 29}, 2, 177);
   private static final String Uv = cvm.q(new byte[]{-114, 5, 27, 29, 17}, 1, 249);
   private static final String oW = cvm.q(new byte[]{-75, 1, 9, 9, 17, 17}, 1, 209);
   private InheritableThreadLocal gO;
   private Permissions nf;
   File q;

   cak() {
      this(false);
   }

   cak(boolean var1) {
      this.gO = new cak.eo(var1);
      this.nf = new Permissions();
      this.nf.add(new RuntimePermission(cvm.q(new byte[]{40, 2, 17, 36, 34, 29, 27, 17, 6, 23, 29, 6, 1, 42, 43, 2, 12, 8, 7}, 1, 79)));
      this.nf.add(new RuntimePermission(cvm.q(new byte[]{11, 2, 17, 55, 47, 13, 18, 0, 63, 35, 14, 5, 1, 23}, 1, 108)));
      this.nf.add(new RuntimePermission(cvm.q(new byte[]{34, 12, 19, 28, 1, 26, 35, 13, 23, 76, 73, 17, 76, 68, 124, 92, 84, 81, 73, 82, 65}, 2, 104)));
      this.nf.add(new RuntimePermission(cvm.q(new byte[]{36, 10, 4, 42, 6, 8, 4, 3, 32, 82, 73, 0, 76}, 2, 121)));
      this.nf.add(new RuntimePermission(cvm.q(new byte[]{36, 10, 4, 58, 30, 8, 20, 27, 56, 79, 73, 7, 76, 82}, 2, 156)));
      this.nf.add(new RuntimePermission(cvm.q(new byte[]{-84, 11, 9, 19, 1, 22, 17, 36, 34, 29, 25, 31, 13, 1, 23}, 1, 207)));
      this.nf
         .add(
            new RuntimePermission(
               cvm.q(
                  new byte[]{
                     -77, 11, 17, 90, 76, 27, 13, 17, 7, 23, 17, 0, 29, 87, 77, 17, 23, 4, 21, 17, 47, 43, 23, 23, 37, 45, 26, 3, 17, 21, 23, 11, 13, 23
                  },
                  1,
                  221
               )
            )
         );
      this.nf
         .add(
            new ReflectPermission(
               cvm.q(
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
                     77,
                     88,
                     69,
                     45,
                     7,
                     5,
                     7,
                     21,
                     75,
                     85,
                     21,
                     7,
                     8,
                     70,
                     27,
                     11
                  },
                  2,
                  65
               )
            )
         );
      this.nf
         .add(
            new ReflectPermission(
               cvm.q(
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
                     77,
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
                  120
               )
            )
         );
      this.nf
         .add(
            new ReflectPermission(
               cvm.q(
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
                     77,
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
                  74
               )
            )
         );
      this.nf
         .add(
            new ReflectPermission(
               cvm.q(
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
                     77,
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
                     4,
                     8,
                     19,
                     4,
                     9,
                     27,
                     0,
                     0,
                     12,
                     28,
                     64
                  },
                  2,
                  167
               )
            )
         );
      this.nf
         .add(
            new ReflectPermission(
               cvm.q(
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
                     77,
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
                     7,
                     15,
                     3,
                     5,
                     8
                  },
                  2,
                  145
               )
            )
         );
      this.nf
         .add(
            new ReflectPermission(
               cvm.q(
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
                     77,
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
                     12,
                     3,
                     18,
                     1,
                     3,
                     13
                  },
                  2,
                  14
               )
            )
         );
      this.nf
         .add(
            new ReflectPermission(
               cvm.q(
                  new byte[]{
                     -29,
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
                  141
               )
            )
         );
      this.nf
         .add(
            new ReflectPermission(
               cvm.q(
                  new byte[]{
                     75,
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
                     90,
                     13,
                     9,
                     21
                  },
                  1,
                  37
               )
            )
         );
      this.nf
         .add(
            new ReflectPermission(
               cvm.q(
                  new byte[]{
                     118,
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
                     29,
                     23,
                     15,
                     12,
                     4,
                     10
                  },
                  1,
                  24
               )
            )
         );
      this.nf
         .add(
            new ReflectPermission(
               cvm.q(
                  new byte[]{
                     -42,
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
                     29,
                     23,
                     15,
                     12,
                     4,
                     10,
                     77,
                     66,
                     3,
                     14,
                     5,
                     13,
                     7,
                     9
                  },
                  1,
                  184
               )
            )
         );
      this.nf.add(new RuntimePermission(cvm.q(new byte[]{32, 29, 21, 24, 6, 12, 36, 4, 21, 83, 91, 47, 70, 65, 85, 92, 75}, 2, 127)));
      this.nf.add(new ReflectPermission(cvm.q(new byte[]{48, 26, 0, 9, 0, 12, 20, 27, 53, 67, 75, 6, 90, 83, 114, 81, 92, 80, 71, 83}, 2, 222)));
      this.nf.add(new RuntimePermission(cvm.q(new byte[]{-54, 2, 0, 6, 22, 0, 48, 47, 13, 18, 0, 58, 39, 62, 49, 2, 8, 10, 6, 2, 75, 4}, 1, 171)));
      this.nf.add(new FilePermission(cvm.q(new byte[]{127, 83, 49, 53, 62, 73, 33, 33, 56, 101, 123, 93, 23}, 2, 207), Dw));
      this.nf.add(new PropertyPermission("*", Dw));
      this.nf.add(new RuntimePermission(cvm.q(new byte[]{47, 0, 17, 29, 62, 0, 5, 26, 21, 82, 81, 77, 90, 85, 95, 92, 90}, 2, 44)));
      this.nf
         .add(
            new RuntimePermission(
               cvm.q(new byte[]{-76, 3, 14, 5, 40, 37, 11, 16, 19, 19, 11, 87, 93, 6, 27, 4, 9, 6, 58, 47, 2, 29, 25, 31, 13, 1, 23}, 1, 216)
            )
         );
      this.nf.add(new RuntimePermission(cvm.q(new byte[]{47, 0, 17, 29, 62, 0, 5, 26, 21, 82, 81, 77, 90, 85, 95, 84, 74, 80, 77, 80, 91}, 2, 205)));
      this.nf.add(new RuntimePermission(cvm.q(new byte[]{-35, 3, 14, 5, 40, 37, 11, 16, 19, 19, 11, 87, 93, 6, 27, 30, 27, 8, 16, 66, 0}, 1, 177)));
      this.nf.add(new RuntimePermission(cvm.q(new byte[]{34, 12, 19, 28, 1, 26, 52, 17, 7, 84, 77, 14, 100, 79, 85, 76, 85, 86, 95}, 2, 134)));
      this.nf.add(new LoggingPermission(cvm.q(new byte[]{32, 0, 30, 13, 0, 6, 11}, 2, 162), ""));
      this.nf
         .add(
            new RuntimePermission(
               cvm.q(
                  new byte[]{38, 1, 17, 27, 30, 12, 36, 7, 26, 84, 77, 27, 93, 99, 93, 88, 74, 64, 96, 79, 83, 84, 87, 70, 99, 86, 42, 0, 19, 10, 8, 0}, 2, 121
               )
            )
         );
      this.nf.add(new RuntimePermission(cvm.q(new byte[]{-108, 3, 14, 5, 40, 37, 11, 16, 19, 19, 11, 87, 84, 19, 25}, 1, 248)));
      this.nf.add(new RuntimePermission(cvm.q(new byte[]{14, 3, 12, 2, 13, 9, 54, 54, 23, 4, 31, 10, 6, 53, 34, 29, 25, 31, 13, 1, 23}, 1, 98)));
   }

   File q(String var1, boolean var2) throws IOException {
      if (var1 == null) {
         throw new IllegalArgumentException(
            cvm.q(
               new byte[]{
                  -5,
                  59,
                  25,
                  0,
                  76,
                  76,
                  3,
                  12,
                  2,
                  21,
                  29,
                  6,
                  1,
                  78,
                  70,
                  9,
                  29,
                  82,
                  83,
                  18,
                  15,
                  10,
                  6,
                  13,
                  23,
                  88,
                  68,
                  22,
                  29,
                  31,
                  0,
                  21,
                  1,
                  68,
                  70,
                  15,
                  5,
                  9,
                  22
               },
               1,
               181
            )
         );
      } else if (this.q != null) {
         return this.q;
      } else {
         File var3 = new File(
            com.pnfsoftware.jeb.util.io.IO.getSessionTemporaryFolder(),
            cvm.q(new byte[]{39, 10, 8, 29, 23, 10, 56, 27, 21, 78, 76, 1, 70, 88, 110, 95, 74}, 2, 18)
         );
         var3.mkdir();
         this.q = new File(var3, var1);
         if (!this.q.getCanonicalPath().startsWith(var3.getCanonicalPath())) {
            throw new IOException();
         } else {
            this.q.mkdir();
            this.q(this.q, true, var2);
            return this.q;
         }
      }
   }

   File q() {
      return this.q;
   }

   private void q(File var1, boolean var2, boolean var3) throws IOException {
      if (var1 != null && var1.isDirectory()) {
         String var4 = var1.getCanonicalPath();
         String var5 = var4 + File.separator + "-";
         if (var2) {
            this.nf.add(new FilePermission(var4, Uv));
            this.nf.add(new FilePermission(var5, Uv));
         }

         if (var3) {
            this.nf.add(new FilePermission(var4, oW));
            this.nf.add(new FilePermission(var5, oW));
         }
      } else {
         throw new IOException(cvm.q(new byte[]{13, 0, 4, 89, 19, 73, 3, 1, 6, 69, 75, 23, 70, 82, 72, 3, 25}, 2, 152) + var1);
      }
   }

   private Boolean q(Permission var1) {
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

   private boolean RF(Permission var1) {
      return this.Dw() || var1 instanceof bxt;
   }

   public void RF() {
      this.gO.set(true);
   }

   public void xK() {
      this.gO.set(false);
   }

   public boolean Dw() {
      return (Boolean)this.gO.get();
   }

   @Override
   public void checkPermission(Permission var1) {
      if (this.RF(var1)) {
         if (!this.nf.implies(var1)) {
            Boolean var2 = this.q(var1);
            if (var2 != null) {
               if (!var2) {
                  throw new SecurityException();
               }
            } else {
               try {
                  super.checkPermission(var1);
               } catch (SecurityException var4) {
                  RF.catchingSilent(var4);
                  throw var4;
               }
            }
         }
      }
   }

   @Override
   public void checkPermission(Permission var1, Object var2) {
      if (this.RF(var1)) {
         if (!this.nf.implies(var1)) {
            Boolean var3 = this.q(var1);
            if (var3 != null) {
               if (!var3) {
                  throw new SecurityException();
               }
            } else {
               try {
                  super.checkPermission(var1, var2);
               } catch (SecurityException var5) {
                  RF.catchingSilent(var5);
                  throw var5;
               }
            }
         }
      }
   }

   private static class eo extends InheritableThreadLocal {
      boolean q;

      eo(boolean var1) {
         this.q = var1;
      }

      protected Boolean q() {
         return this.q;
      }

      public void q(Boolean var1) {
         if (!var1) {
            SecurityManager var2 = System.getSecurityManager();
            if (var2 != null) {
               var2.checkPermission(cak.xK);
            }
         }

         super.set(var1);
      }
   }
}
