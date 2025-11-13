package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInvokeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class ces extends AbstractDOptimizer {
   protected static final ILogger q = GlobalLog.getLogger(ces.class, Integer.MAX_VALUE);
   private static long gO = -1L;
   public static long RF = 5000L;
   public static int xK = 20;
   protected Set Dw = new HashSet(Arrays.asList("Ljava/util/Iterator;->next()Ljava/lang/Object;"));
   protected boolean Uv;
   protected boolean oW;
   private long nf;
   private long gP = RF;
   private int za = xK;
   private boolean lm;

   private void q(String var1, Object... var2) {
   }

   private void RF(String var1, Object... var2) {
   }

   public ces() {
      this(true);
   }

   protected ces(boolean var1) {
      super(DOptimizerType.NORMAL);
      this.getPluginInformation().setDescription(S.L("Variables propagation"));
      if (var1) {
         this.Uv = false;

         for (String var5 : new String[0]) {
            this.Dw.add(var5);
         }
      }
   }

   public void q(long var1) {
      this.gP = var1;
   }

   public void q(int var1) {
      this.za = var1;
   }

   private boolean q() {
      return com.pnfsoftware.jeb.corei.parsers.dexdec.eo.RF ? false : this.gP >= 0L && System.currentTimeMillis() - this.nf >= this.gP;
   }

   @Override
   public int perform() {
      this.nf = System.currentTimeMillis();
      if (this.Uv) {
         return this.RF(0)[0];
      } else {
         this.analyzeChains();
         int[] var1 = this.RF(1);
         int var2 = var1[0];
         if (var1[1] > 0) {
            var1 = this.RF(2);
            var2 += var1[0];
         }

         if (var2 > 0) {
            this.invalidateChains();
         }

         return var2;
      }
   }

   private int[] RF(int var1) {
      long var2 = System.currentTimeMillis();
      int var4 = 0;
      int var5 = 0;

      int var8;
      for (BasicBlock var7 : this.cfg) {
         do {
            this.checkInterrupted();
            var8 = 0;
            int var9 = 0;

            label421:
            while (var9 < var7.size()) {
               IDInstruction var10 = (IDInstruction)var7.get(var9);
               if (!var10.isAssignToVar()) {
                  var9++;
               } else {
                  if (gO >= 0L && var4 == 0 && System.currentTimeMillis() - var2 >= gO || this.q()) {
                     return new int[]{var4, var5};
                  }

                  long var11 = var10.getOffset();
                  IDVar var13 = (IDVar)var10.getAssignDestination();
                  IDExpression var14 = var10.getAssignSource();
                  boolean var15 = DUtil.hasInvokeInfo(var10);
                  if (var15 && this.Dw != null && var14 instanceof IDCallInfo var16 && this.Dw.contains(var16.getMethodSignature())) {
                     var9++;
                  } else {
                     Watchdog.verify(this.ctx.getWatchdog());
                     int var36 = var13.getId();
                     Set var17 = var14.getVarIds();
                     int var18 = var14 instanceof IDVar var19 ? var19.getId() : -1;
                     Object var37;
                     if (this.za >= 0 && !(var14 instanceof IDVar) && !(var14 instanceof IDImm)) {
                        var37 = this.dfa.getDefUses(var11, var36, this.za);
                        if (var37.size() >= this.za) {
                           var9++;
                           continue;
                        }
                     } else {
                        var37 = this.dfa.getDefUses(var11, var36);
                     }

                     if (this.q()) {
                        return new int[]{var4, var5};
                     }

                     if (var1 == 1 && var14 instanceof IDInvokeInfo) {
                        boolean var20 = false;
                        if (var37.size() == 1) {
                           long var21 = (Long)var37.iterator().next();
                           IDInstruction var23 = (IDInstruction)this.cfg.getInstruction(var21);
                           if (var23 != null
                              && var23.isAssign()
                              && var23.getAssignSource() instanceof IDOperation var25
                              && var25.isCast()
                              && var25.getRight() == var13) {
                              var20 = true;
                           }
                        }

                        if (!var20) {
                           var5++;
                           var9++;
                           continue;
                        }
                     }

                     for (long var40 : var37) {
                        if (!this.dfa.checkSingleDef(var40, var36, var11)) {
                           Object[] var79 = new Object[0];
                           var9++;
                           continue label421;
                        }

                        if (this.q()) {
                           return new int[]{var4, var5};
                        }
                     }

                     if (var37.isEmpty()) {
                        var9++;
                     } else {
                        boolean var39 = false;
                        if (!var17.isEmpty()) {
                           ArrayList var41 = null;

                           for (long var45 : var37) {
                              for (Integer var26 : var17) {
                                 Boolean var27 = this.dfa.isVarReachingFromTo(var26, var11, var45);
                                 if (!Boolean.TRUE.equals(var27)) {
                                    if (var18 < 0) {
                                       Object[] var81 = new Object[0];
                                       var9++;
                                       continue label421;
                                    }

                                    Object[] var80 = new Object[0];
                                    if (var41 == null) {
                                       var41 = new ArrayList();
                                    }

                                    var41.add(var45);
                                    break;
                                 }

                                 if (this.q()) {
                                    return new int[]{var4, var5};
                                 }
                              }
                           }

                           if (var41 != null) {
                              var37 = new LinkedHashSet((Collection)var37);
                              var37.removeAll(var41);
                              if (var37.isEmpty()) {
                                 var9++;
                                 continue;
                              }

                              int var43 = 0;

                              for (long var50 : var37) {
                                 IDInstruction var59 = (IDInstruction)this.cfg.getInstructionAt(var50);
                                 if (var59.isAssignFromVarToVar(var36, var18)) {
                                    var43++;
                                 }
                              }

                              if (var43 == 0) {
                                 var9++;
                                 continue;
                              }

                              var39 = true;
                           }
                        }

                        Integer var42 = null;
                        IdentityHashMap var44 = new IdentityHashMap();

                        for (long var51 : var37) {
                           Couple var60 = this.cfg.getInstructionLocation(var51);
                           BasicBlock var63 = (BasicBlock)var60.getFirst();
                           Integer var28 = (Integer)var60.getSecond();
                           if (var63 == var7) {
                              if (var42 == null || var28 > var42) {
                                 var42 = var28;
                              }
                           } else {
                              Integer var29 = (Integer)var44.get(var63);
                              if (var29 == null || var28 > var29) {
                                 var44.put(var63, var28);
                              }
                           }
                        }

                        if (var15 && !var44.isEmpty()) {
                           boolean var48 = true;
                           if (var48 && var44.size() == 1) {
                              BasicBlock var52 = (BasicBlock)var44.keySet().iterator().next();
                              if (var7.outsize() == 1 && var52.irrinsize() == 0 && var52.insize() == 1 && var52.getInputBlock(0) == var7) {
                                 var48 = false;
                              }
                           }

                           if (var48) {
                              var9++;
                              continue;
                           }
                        }

                        boolean var49 = DUtil.usesReferences(var10);
                        if (var49 && !var44.isEmpty()) {
                           boolean var53 = true;
                           if (var53 && var14 instanceof IDInstanceField var56 && var56.isArrayLength()) {
                              var53 = false;
                           }

                           if (var53 && var44.size() == 1) {
                              BasicBlock var57 = (BasicBlock)var44.keySet().iterator().next();
                              if (var7.outsize() == 1 && var57.irrinsize() == 0 && var57.insize() == 1 && var57.getInputBlock(0) == var7) {
                                 var53 = false;
                              }
                           }

                           if (var53) {
                              var9++;
                              continue;
                           }
                        }

                        boolean var54 = var10.canThrow();
                        IDTryData var58 = this.ctx.getExceptionData();
                        if (!var58.isEmpty()) {
                           if (var54) {
                              boolean var61 = false;
                              if (this.oW && var14 instanceof IDCallInfo var64 && chl.za.contains(var64.getMethodSignature())) {
                                 var61 = true;

                                 for (IDExpression var71 : var64.getArguments()) {
                                    if (var71.canThrow(this.ctx)) {
                                       var61 = false;
                                       break;
                                    }
                                 }
                              }

                              if (!var61) {
                                 for (BasicBlock var68 : var44.keySet()) {
                                    if (!var58.compareHandlers((int)var7.getAddress(), (int)var68.getAddress())) {
                                       var9++;
                                       continue label421;
                                    }
                                 }
                              }
                           } else {
                              var58.isProtectedBlock((int)var7.getBase());
                           }
                        }

                        ArrayList var62 = new ArrayList(var37.size());
                        var37.forEach(var2x -> var62.add((IDInstruction)this.cfg.getInstruction(var2x)));
                        int var66 = 0;
                        if (var62.size() >= 2) {
                           if (var15) {
                              var9++;
                              continue;
                           }

                           if (DUtil.collectVars(var14).size() >= 2) {
                              var9++;
                              continue;
                           }

                           if (DUtil.calculateComplexity(var14) >= 5) {
                              var9++;
                              continue;
                           }
                        }

                        for (IDInstruction var72 : var62) {
                           int var30 = var72.countUsedVariable(var13);
                           if (var30 >= 2) {
                              boolean var31;
                              if (var14 instanceof IDVar
                                 || var14 instanceof IDStaticField
                                 || var14 instanceof IDInstanceField var32 && var32.getInstance() instanceof IDVar) {
                                 var31 = true;
                              } else if (var30 == 2 && var14 instanceof IDOperation) {
                                 var31 = true;

                                 for (IDExpression var35 : var14.getSubExpressions()) {
                                    if (!(var35 instanceof IDImm) && !(var35 instanceof IDVar)) {
                                       var31 = false;
                                       break;
                                    }
                                 }

                                 if (!var72.visitDepthPre(new cet(this, var30, var13))) {
                                    var31 = false;
                                 }
                              } else {
                                 var31 = false;
                              }

                              if (!var31) {
                                 var9++;
                                 continue label421;
                              }
                           }

                           var66 += var30;
                        }

                        if (var66 == 0) {
                           var9++;
                        } else {
                           chu var70 = new chu(this.ctx, var13, var14, var10);
                           if (var70.q()) {
                              boolean var73 = false;
                              if (var42 == null) {
                                 if (!var70.q(var7, var9 + 1, var7.size(), false)) {
                                    var73 = true;
                                 } else {
                                    for (BasicBlock var77 : var44.keySet()) {
                                       int var78 = (Integer)var44.get(var77);
                                       if (!var70.q(var77, 0, var78, true)) {
                                          var73 = true;
                                          break;
                                       }
                                    }
                                 }
                              } else if (!var44.isEmpty()) {
                                 var73 = true;
                              } else {
                                 var73 = !var70.q(var7, var9 + 1, var42, true);
                              }

                              if (var73) {
                                 var9++;
                                 continue;
                              }
                           }

                           if (var54) {
                              this.dfa.invalidate();
                           } else {
                              this.dfa.invalidateForSubstitution(var11, (Collection)var37, null);
                           }

                           for (IDInstruction var76 : var62) {
                              var76.replaceUsedVariable(var13, var14);
                           }

                           if (!var39) {
                              var10.transformToNop();
                           }

                           if (++var8 % 50 == 0) {
                              this.checkInterrupted();
                           }

                           var4++;
                        }
                     }
                  }
               }
            }
         } while (var8 > 0);
      }

      return new int[]{var4, var5};
   }
}
