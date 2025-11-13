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

public class bme {
   private static final ILogger q = GlobalLog.getLogger(bme.class);
   private IDMethodContext RF;
   private CFG xK;
   private bsv Dw;
   private TreeMap Uv;

   public bme(IDMethodContext var1) {
      this.RF = var1;
   }

   public TreeMap q() {
      return this.Uv;
   }

   public boolean RF() {
      if (this.Uv != null) {
         throw new IllegalStateException();
      } else {
         this.Uv = new TreeMap();
         IDTryData var1 = this.RF.getExceptionData();
         if (var1.isEmpty()) {
            return true;
         } else {
            this.xK = this.RF.getCfg();
            ArrayList var2 = new ArrayList();
            ArrayList var3 = new ArrayList();
            this.xK.getGraphRepresentation(var2, var3);
            this.Dw = new bsv(var2, var3);
            int var4 = 1;
            TreeMap var5 = new TreeMap();

            for (BasicBlock var7 : this.xK) {
               var5.put((int)var7.getBase(), var4);
               var4++;
            }

            ArrayList var24 = new ArrayList();
            TreeMap var25 = this.xK.getInstructionSet();

            for (BasicBlock var9 : this.xK) {
               if (var9.size() == 2
                  && ((IDInstruction)var9.get(0)).getOpcode() == DOpcodeType.IR_STORE_EXCEPTION
                  && ((IDInstruction)var9.get(1)).getOpcode() == DOpcodeType.IR_JUMP) {
                  int var10 = ((bul)((IDInstruction)var9.get(1)).getOperands()[0]).getOffset();
                  Long var11 = (Long)var25.lowerKey((long)var10);
                  if (var11 != null) {
                     IDInstruction var12 = (IDInstruction)var25.get(var11);
                     if (var12.getOpcode() == DOpcodeType.IR_STORE_EXCEPTION) {
                        var24.add((Integer)var5.get((int)var9.getFirstAddress()));
                     }
                  }
               }
            }

            List var26 = this.xK.getBlocks();
            bix var27 = ((btn)var1).RF(var26);
            List var28 = var27.xK();
            Object[] var10000 = new Object[]{var28};
            var10000 = new Object[0];
            ArrayList var29 = new ArrayList();

            for (List var13 : var28) {
               label212:
               while (!var13.isEmpty()) {
                  biw var14 = (biw)var13.remove(0);
                  int var15 = var14.RF();
                  biw var16 = null;

                  for (biw var18 : var13) {
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
                        BasicBlock var61 = this.xK.get(var54);
                        if (var61.canThrow()) {
                           var29.add(var14);
                           continue label212;
                        }
                     }

                     var10000 = new Object[]{var14, var16};
                     var14.q(var16.RF() - var14.getTryAddress());
                     var13.remove(var16);
                     var13.add(0, var14);
                  }
               }
            }

            var10000 = new Object[0];

            label185:
            for (biw var34 : var29) {
               int var37 = var34.RF();
               int var40 = (Integer)var34.nf().stream().min(Integer::compare).get();
               if (var37 < var40) {
                  for (int var47 = var37; var47 < var40; var47++) {
                     BasicBlock var55 = this.xK.get(var47);
                     if (var55.canThrow()) {
                        continue label185;
                     }
                  }

                  int var48 = var40 - var34.getTryAddress();
                  var10000 = new Object[]{var34, var34.getTrySize(), var48};
                  var34.q(var48);
               }
            }

            var10000 = new Object[]{var29};

            for (biw var35 : var29) {
               bmg var38 = new bmg(var35.getTryAddress() + 1, var35.RF());
               Maps.putMulti(this.Uv, var38.q, var38);
               List var41 = var38.xK;

               for (biv var56 : var35.getHandlers()) {
                  String var62 = var56.isTyped() ? this.RF.getDex().getType(var56.RF()).getSignature(false) : "Ljava/lang/Throwable;";
                  IJavaType var19 = this.RF.getTypeFactory().createType(var62);
                  IDInstruction var20 = (IDInstruction)this.xK.get(var56.getAddress()).get(0);
                  IDInstruction var21 = var20.getOpcode() == DOpcodeType.IR_STORE_EXCEPTION ? var20 : null;
                  int var22 = var56.getAddress() + 1;
                  if (!var41.isEmpty() && ((bll)var41.get(var41.size() - 1)).q == var22) {
                     ((bll)var41.get(var41.size() - 1)).Dw.add(var19);
                  } else {
                     bll var23 = new bll(var22, var19, var21);
                     var41.add(var23);
                  }
               }
            }

            var10000 = new Object[]{this.Uv};
            ArrayList var33 = new ArrayList(Maps.collectMulti(this.Uv));
            Collections.reverse(var33);

            label154:
            for (bmg var39 : var33) {
               if (var39.RF + 1 == ((bll)var39.xK.get(0)).q) {
                  for (int var42 = 0; var42 < var39.xK.size() - 1; var42++) {
                     bll var50 = (bll)var39.xK.get(var42);
                     bll var57 = (bll)var39.xK.get(var42 + 1);
                     if (var50.q >= var57.q) {
                        continue label154;
                     }
                  }

                  if (var39.xK.size() == 1) {
                     List var43 = this.Dw.oW(var39.RF);
                     if (var43.size() == 1 && (Integer)var43.get(0) == ((bll)var39.xK.get(0)).q) {
                        var10000 = new Object[]{var39.xK.get(0)};
                        ((bll)var39.xK.get(0)).RF = -1;
                        continue;
                     }
                  }

                  for (int var44 = 0; var44 < var39.xK.size() - 1; var44++) {
                     bll var51 = (bll)var39.xK.get(var44);
                     bll var58 = (bll)var39.xK.get(var44 + 1);
                     int var63 = var51.q;
                     int var64 = var58.q - 1;

                     for (bmg var66 : var33) {
                        if (var66.q < var63 ? var66.RF >= var63 && var66.RF < var64 : var66.q <= var64 && var66.RF > var64) {
                           continue label154;
                        }
                     }
                  }

                  for (int var45 = 0; var45 < var39.xK.size() - 1; var45++) {
                     bll var52 = (bll)var39.xK.get(var45);
                     bll var59 = (bll)var39.xK.get(var45 + 1);
                     var52.RF = var59.q - 1;
                     var10000 = new Object[]{var52};
                  }

                  int var46 = this.Dw.gO() + 1;
                  bll var53 = (bll)var39.xK.get(var39.xK.size() - 1);
                  int var60 = this.q(var53.q, var24, var46);
                  if (var60 > var53.q) {
                     var10000 = new Object[]{var53, var60};
                     var53.RF = var60;
                  }

                  var39.Dw = true;
               }
            }

            return true;
         }
      }
   }

   private int q(int var1, List var2, int var3) {
      if (var3 == var1 + 1) {
         return var1;
      } else {
         int var4 = var1 + 1;

         label77:
         for (int var5 = this.Dw.gO(); var4 <= var5; var4++) {
            for (int var7 : this.Dw.Uv(var4)) {
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
                  for (int var13 : this.Dw.nf(var11)) {
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
