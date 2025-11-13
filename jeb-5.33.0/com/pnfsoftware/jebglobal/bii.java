package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class bii {
   private static final ILogger pC = GlobalLog.getLogger(bii.class);
   private IDMethodContext A;
   private CFG kS;
   private TreeMap wS;

   public bii(IDMethodContext var1) {
      this.A = var1;
   }

   public TreeMap pC() {
      return this.wS;
   }

   public boolean A() {
      if (this.wS != null) {
         throw new IllegalStateException();
      } else {
         this.wS = new TreeMap();
         IDTryData var1 = this.A.getExceptionData();
         if (var1.isEmpty()) {
            return true;
         } else {
            this.kS = this.A.getCfg();

            for (BasicBlock var3 : this.kS) {
               for (BasicBlock var5 : var3.getIrregularInputs()) {
                  if (var5.getBase() >= var3.getBase()) {
                     Object[] var10000 = new Object[]{var5, var3};
                     return false;
                  }
               }
            }

            HashMap var25 = new HashMap();
            int var26 = 0;

            for (BasicBlock var30 : this.kS) {
               long var6 = var30.getBase();
               var25.put(var6, new bii.Av(var26, var1.getBlockHandlers((int)var6)));
               var26++;
            }

            int var29 = -1;
            int var31 = -1;

            for (int var27 = 0; var27 < this.kS.size(); var27++) {
               BasicBlock var32 = this.kS.get(var27);
               if (var32.irrinsize() != 0) {
                  int var7 = var27;
                  TreeSet var8 = new TreeSet();

                  for (BasicBlock var10 : var32.getIrregularInputs()) {
                     int var11 = ((bii.Av)var25.get(var10.getBase())).pC;
                     Assert.a(var11 < var7);
                     var8.add(var11);
                  }

                  Assert.a(!var8.isEmpty());
                  int var41 = var1.getHandledExceptionTypesAt((int)var32.getBase()).size();
                  Assert.a(var41 >= 1);
                  IDExceptionHandler[] var46 = new IDExceptionHandler[var41];
                  int[] var50 = new int[var41];
                  int var12 = -1;

                  for (int var14 : var8) {
                     bii.Av var15 = (bii.Av)var25.get(this.kS.get(var14).getBase());
                     if (var15.A.size() < var41) {
                        return false;
                     }

                     if (var12 < 0 && this.kS.get(var14).canThrow()) {
                        var12 = var14;
                     }
                  }

                  if (var12 < 0) {
                     var12 = (Integer)var8.iterator().next();
                  }

                  bii.Av var55 = (bii.Av)var25.get(this.kS.get(var12).getBase());

                  for (int var58 = 0; var58 < var41; var58++) {
                     IDExceptionHandler var62 = (IDExceptionHandler)var55.A.get(var58);
                     var46[var58] = var62;
                     var50[var58] = var62.getTypeIndex();
                  }

                  for (int var63 : var8) {
                     bii.Av var16 = (bii.Av)var25.get(this.kS.get(var63).getBase());
                     boolean var17 = this.kS.get(var63).canThrow();

                     for (int var18 = 0; var18 < var41; var18++) {
                        IDExceptionHandler var19 = (IDExceptionHandler)var16.A.remove(0);
                        if (var17 && !var19.equals(var46[var18])) {
                           return false;
                        }

                        var16.kS.add(var19.getTypeIndex());
                     }
                  }

                  for (int var66 : pC(var8)) {
                     BasicBlock var68 = this.kS.get(var66);
                     if (var68.canThrow()) {
                        for (int var21 : var50) {
                           if (!((bii.Av)var25.get(var68.getBase())).pC(var21)) {
                              return false;
                           }
                        }
                     }

                     var8.add(var66);
                  }

                  int var65 = (Integer)var8.first();
                  int var67 = (Integer)var8.last();
                  bij var71 = null;
                  int var69;
                  if (var29 == -1) {
                     var69 = var7 - 1;
                  } else if (var65 < var29) {
                     var69 = var7 - 1;
                  } else if (var65 > var31) {
                     var69 = var7 - 1;
                  } else {
                     List var73 = (List)this.wS.get(var65);
                     if (var73 != null && !var73.isEmpty()) {
                        bij var76 = (bij)var73.get(var73.size() - 1);
                        if (var67 <= var76.A) {
                           var71 = var76;
                           var69 = var76.A;
                        } else {
                           var69 = var7 - 1;
                        }
                     } else {
                        var69 = var7 - 1;
                     }
                  }

                  if (var69 >= 0) {
                     for (int var74 = var67 + 1; var74 <= var69; var74++) {
                        BasicBlock var77 = this.kS.get(var74);
                        if (var77.canThrow()) {
                           for (int var24 : var50) {
                              if (!((bii.Av)var25.get(var77.getBase())).pC(var24)) {
                                 return false;
                              }
                           }
                        }

                        var8.add(var74);
                     }

                     var67 = var69;
                  }

                  bij var75 = null;
                  if (var29 != -1) {
                     Assert.a(var31 != -1);
                     if (var65 <= var31 && (var65 > var29 || var67 < var31)) {
                        if (var71 != null) {
                           var75 = var71;
                        } else {
                           for (List var82 : this.wS.headMap(var65).values()) {
                              for (bij var86 : var82) {
                                 if (var65 <= var86.kS()) {
                                    bho var88 = var86.A();
                                    if (var88 == null || var88.pC != var65) {
                                       return false;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }

                  if (var75 == null) {
                     var75 = new bij(var65, var67);
                     var75.wS = true;
                     Object var79 = (List)this.wS.get(var65);
                     if (var79 == null) {
                        var79 = new ArrayList();
                        this.wS.put(var65, var79);
                     }

                     var79.add(var75);
                  }

                  IJavaType var80 = this.A(var50[0]);
                  IDInstruction var83 = ((IDInstruction)var32.get(0)).isStoreException() ? (IDInstruction)var32.get(0) : null;
                  bho var85 = new bho(var7, var80, var83);

                  for (int var87 = 1; var87 < var50.length; var87++) {
                     var85.wS.add(this.A(var50[var87]));
                  }

                  var75.kS.add(var85);
                  if (var29 == -1 || var65 < var29) {
                     var29 = var65;
                  }

                  if (var31 == -1 || var7 > var31) {
                     var31 = var7;
                  }
               }
            }

            for (List var35 : this.wS.values()) {
               for (bij var42 : var35) {
                  for (int var47 = 0; var47 < var42.kS.size() - 1; var47++) {
                     ((bho)var42.kS.get(var47)).A = ((bho)var42.kS.get(var47 + 1)).pC - 1;
                  }
               }
            }

            TreeMap var34 = new TreeMap();
            TreeMap var36 = new TreeMap();

            for (List var43 : this.wS.values()) {
               for (bij var51 : var43) {
                  for (bho var56 : var51.kS) {
                     var34.put(var56.pC, var51);
                  }

                  var36.put(var51.A().pC, var51);
               }
            }

            for (int var44 : var36.descendingKeySet()) {
               bij var49 = (bij)var36.get(var44);
               int var52 = var44;
               if (this.kS.get(var44).insize() > 0) {
                  var52 = -1;
               } else {
                  for (int var54 = var44; var54 < this.kS.size(); var54++) {
                     bij var57 = this.pC(var54);
                     if (var57 != null) {
                        int var61 = var57.kS();
                        if (var61 >= var54) {
                           var54 = var61;
                        }
                     } else if (var54 != var44 && var34.containsKey(var54)) {
                        break;
                     }

                     if (this.pC(var25, var44, var54)) {
                        var52 = var54;
                     }
                  }
               }

               var49.A().A = var52;
            }

            for (List var45 : this.wS.values()) {
               Lists.reverse(var45);
            }

            this.kS();
            return true;
         }
      }
   }

   private boolean pC(Map var1, int var2, int var3) {
      for (int var4 = var2; var4 <= var3; var4++) {
         for (BasicBlock var6 : this.kS.get(var4).getInputs()) {
            int var7 = ((bii.Av)var1.get(var6.getBase())).pC;
            if (var7 < var2 || var7 > var3) {
               return false;
            }
         }
      }

      return true;
   }

   private bij pC(int var1) {
      List var2 = (List)this.wS.get(var1);
      return var2 != null && !var2.isEmpty() ? (bij)var2.get(var2.size() - 1) : null;
   }

   private IJavaType A(int var1) {
      String var2 = var1 == -1 ? "Ljava/lang/Throwable;" : this.A.getDex().getType(var1).getSignature(false);
      return this.A.getTypeFactory().createType(var2);
   }

   private void kS() {
      TreeMap var1 = new TreeMap();

      for (Entry var3 : this.wS.entrySet()) {
         List var4 = (List)var3.getValue();

         for (bij var6 : var4) {
            var6.pC++;
            var6.A++;

            for (bho var8 : var6.kS) {
               var8.pC++;
               if (var8.A >= 0) {
                  var8.A++;
               }
            }
         }

         var1.put((Integer)var3.getKey() + 1, var4);
      }

      this.wS = var1;
   }

   static SortedSet pC(SortedSet var0) {
      Assert.a(!var0.isEmpty());
      int var1 = (Integer)var0.first();
      int var2 = (Integer)var0.last();
      if (var2 - var1 + 1 == var0.size()) {
         return Collections.emptySortedSet();
      } else {
         TreeSet var3 = new TreeSet();

         for (int var4 = var1 + 1; var4 < var2; var4++) {
            if (!var0.contains(var4)) {
               var3.add(var4);
            }
         }

         return var3;
      }
   }

   class Av {
      int pC;
      List A;
      Set kS = new HashSet();

      Av(int var2, List var3) {
         this.pC = var2;
         this.A = new ArrayList(var3);
      }

      boolean pC(int var1) {
         if (this.kS.contains(var1)) {
            return true;
         } else {
            String var2 = bpl.pC(bii.this.A, var1);
            if (var2 != null) {
               for (int var4 : this.kS) {
                  String var5 = bpl.pC(bii.this.A, var4);
                  if (var5 != null && bii.this.A.getTypeInfoProvider().isCompatible(var2, var5)) {
                     return true;
                  }
               }
            }

            return false;
         }
      }
   }
}
