package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

public class bih {
   private static final ILogger pC = GlobalLog.getLogger(bih.class);
   private IDMethodContext A;
   private CFG kS;
   private bou wS;
   private TreeMap UT;

   public bih(IDMethodContext var1) {
      this.A = var1;
   }

   public TreeMap pC() {
      return this.UT;
   }

   public boolean A() {
      if (this.UT != null) {
         throw new IllegalStateException();
      } else {
         this.UT = new TreeMap();
         IDTryData var1 = this.A.getExceptionData();
         if (var1.isEmpty()) {
            return true;
         } else {
            this.kS = this.A.getCfg();
            ArrayList var2 = new ArrayList();
            ArrayList var3 = new ArrayList();
            this.kS.getGraphRepresentation(var2, var3);
            this.wS = new bou(var2, var3);
            int var4 = 1;
            TreeMap var5 = new TreeMap();

            for (BasicBlock var7 : this.kS) {
               var5.put((int)var7.getBase(), var4);
               var4++;
            }

            ArrayList var24 = new ArrayList();
            TreeMap var25 = this.kS.getInstructionSet();

            for (BasicBlock var9 : this.kS) {
               if (var9.size() == 2
                  && ((IDInstruction)var9.get(0)).getOpcode() == DOpcodeType.IR_STORE_EXCEPTION
                  && ((IDInstruction)var9.get(1)).getOpcode() == DOpcodeType.IR_JUMP) {
                  int var10 = ((bqf)((IDInstruction)var9.get(1)).getOperands()[0]).getOffset();
                  Long var11 = (Long)var25.lowerKey((long)var10);
                  if (var11 != null) {
                     IDInstruction var12 = (IDInstruction)var25.get(var11);
                     if (var12.getOpcode() == DOpcodeType.IR_STORE_EXCEPTION) {
                        var24.add((Integer)var5.get((int)var9.getFirstAddress()));
                     }
                  }
               }
            }

            List var26 = this.kS.getBlocks();
            bfc var27 = ((bpk)var1).pC(var26);
            List var28 = var27.A();
            Object[] var10000 = new Object[]{var28};
            var10000 = new Object[0];
            ArrayList var29 = new ArrayList();

            for (List var13 : var28) {
               label212:
               while (!var13.isEmpty()) {
                  bfb var14 = (bfb)var13.remove(0);
                  int var15 = var14.A();
                  bfb var16 = null;

                  for (bfb var18 : var13) {
                     if (var18.getTryAddress() >= var15) {
                        var16 = var18;
                        break;
                     }
                  }

                  if (var16 == null) {
                     var29.add(var14);
                  } else if (!var14.getHandlers().equals(var16.getHandlers())) {
                     var29.add(var14);
                  } else {
                     for (int var54 = var15; var54 < var16.getTryAddress(); var54++) {
                        BasicBlock var61 = this.kS.get(var54);
                        if (var61.canThrow()) {
                           var29.add(var14);
                           continue label212;
                        }
                     }

                     var10000 = new Object[]{var14, var16};
                     var14.pC(var16.A() - var14.getTryAddress());
                     var13.remove(var16);
                     var13.add(0, var14);
                  }
               }
            }

            var10000 = new Object[0];

            label185:
            for (bfb var34 : var29) {
               int var37 = var34.A();
               int var40 = (Integer)var34.UT().stream().min(Integer::compare).get();
               if (var37 < var40) {
                  for (int var47 = var37; var47 < var40; var47++) {
                     BasicBlock var55 = this.kS.get(var47);
                     if (var55.canThrow()) {
                        continue label185;
                     }
                  }

                  int var48 = var40 - var34.getTryAddress();
                  var10000 = new Object[]{var34, var34.getTrySize(), var48};
                  var34.pC(var48);
               }
            }

            var10000 = new Object[]{var29};

            for (bfb var35 : var29) {
               bij var38 = new bij(var35.getTryAddress() + 1, var35.A());
               Maps.putMulti(this.UT, var38.pC, var38);
               List var41 = var38.kS;

               for (bfa var56 : var35.getHandlers()) {
                  String var62 = var56.isTyped() ? this.A.getDex().getType(var56.A()).getSignature(false) : "Ljava/lang/Throwable;";
                  IJavaType var19 = this.A.getTypeFactory().createType(var62);
                  IDInstruction var20 = (IDInstruction)this.kS.get(var56.getAddress()).get(0);
                  IDInstruction var21 = var20.getOpcode() == DOpcodeType.IR_STORE_EXCEPTION ? var20 : null;
                  int var22 = var56.getAddress() + 1;
                  if (!var41.isEmpty() && ((bho)var41.get(var41.size() - 1)).pC == var22) {
                     ((bho)var41.get(var41.size() - 1)).wS.add(var19);
                  } else {
                     bho var23 = new bho(var22, var19, var21);
                     var41.add(var23);
                  }
               }
            }

            var10000 = new Object[]{this.UT};
            ArrayList var33 = new ArrayList(Maps.collectMulti(this.UT));
            Collections.reverse(var33);

            label154:
            for (bij var39 : var33) {
               if (var39.A + 1 == ((bho)var39.kS.get(0)).pC) {
                  for (int var42 = 0; var42 < var39.kS.size() - 1; var42++) {
                     bho var50 = (bho)var39.kS.get(var42);
                     bho var57 = (bho)var39.kS.get(var42 + 1);
                     if (var50.pC >= var57.pC) {
                        continue label154;
                     }
                  }

                  if (var39.kS.size() == 1) {
                     List var43 = this.wS.wS(var39.A);
                     if (var43.size() == 1 && (Integer)var43.get(0) == ((bho)var39.kS.get(0)).pC) {
                        var10000 = new Object[]{var39.kS.get(0)};
                        ((bho)var39.kS.get(0)).A = -1;
                        continue;
                     }
                  }

                  for (int var44 = 0; var44 < var39.kS.size() - 1; var44++) {
                     bho var51 = (bho)var39.kS.get(var44);
                     bho var58 = (bho)var39.kS.get(var44 + 1);
                     int var63 = var51.pC;
                     int var64 = var58.pC - 1;

                     for (bij var66 : var33) {
                        if (var66.pC < var63 ? var66.A >= var63 && var66.A < var64 : var66.pC <= var64 && var66.A > var64) {
                           continue label154;
                        }
                     }
                  }

                  for (int var45 = 0; var45 < var39.kS.size() - 1; var45++) {
                     bho var52 = (bho)var39.kS.get(var45);
                     bho var59 = (bho)var39.kS.get(var45 + 1);
                     var52.A = var59.pC - 1;
                     var10000 = new Object[]{var52};
                  }

                  int var46 = this.wS.A() + 1;
                  bho var53 = (bho)var39.kS.get(var39.kS.size() - 1);
                  int var60 = this.pC(var53.pC, var24, var46);
                  if (var60 > var53.pC) {
                     var10000 = new Object[]{var53, var60};
                     var53.A = var60;
                  }

                  var39.wS = true;
               }
            }

            return true;
         }
      }
   }

   private int pC(int var1, List var2, int var3) {
      if (var3 == var1 + 1) {
         return var1;
      } else {
         int var4 = var1 + 1;

         label77:
         for (int var5 = this.wS.A(); var4 <= var5; var4++) {
            for (int var7 : this.wS.kS(var4)) {
               if (var7 < var1 && !var2.contains(var7)) {
                  break label77;
               }
            }
         }

         int var14 = var4 - 1;
         if (var14 == var1) {
            return var1;
         } else {
            if (var14 >= var3) {
               var14 = var3 - 1;
            }

            HashSet var15 = new HashSet();
            var15.add(var1);
            HashSet var8 = new HashSet();
            var8.add(var1);

            while (!var15.isEmpty()) {
               HashSet var9 = new HashSet();

               for (int var11 : var15) {
                  for (int var13 : this.wS.UT(var11)) {
                     if (var13 >= var1 && var13 <= var14 && !var8.contains(var13)) {
                        var9.add(var13);
                     }
                  }
               }

               var8.addAll(var15);
               var15 = var9;
            }

            for (int var16 = var1; var16 <= var14; var16++) {
               if (!var8.contains(var16)) {
                  return var16 - 1;
               }
            }

            return var14;
         }
      }
   }
}
