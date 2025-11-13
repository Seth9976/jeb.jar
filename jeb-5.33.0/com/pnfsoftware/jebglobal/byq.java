package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class byq extends AbstractDOptimizer {
   public byq() {
      this.addTag("inliner");
   }

   @Override
   public int perform() {
      if (bpl.pC(this.g)) {
         return 0;
      } else {
         int var1 = 0;

         for (BasicBlock var3 : this.cfg) {
            if (var3.irrinsize() > 0 && var3.size() == 2 && DUtil.checkBlock(var3, DOpcodeType.IR_STORE_EXCEPTION, DOpcodeType.IR_INVOKE)) {
               IDVar var4 = ((IDInstruction)var3.get(0)).getStoredExceptionVariable();
               IDInstruction var5 = (IDInstruction)var3.get(1);
               if (var5.getInvokeData() instanceof IDCallInfo) {
                  IDCallInfo var6 = (IDCallInfo)var5.getInvokeData();
                  if (var6.getInvokeType() == DInvokeType.STATIC
                     && var6.getCountOfArguments() == 2
                     && var6.getArgument(1) == var4
                     && var6.getArgument(0) instanceof IDVar) {
                     IDexMethod var7 = bpl.pC(var6, this.dex);
                     if (var7 != null) {
                        IDVar var8 = (IDVar)var6.getArgument(0);
                        this.analyzeChains();
                        Long var9 = this.dfa.checkSingleDef(var5.getOffset(), var8.getId());
                        IDInstruction var10;
                        if (var9 != null && (var10 = (IDInstruction)this.cfg.getInstruction(var9)) != null && var10.isStoreException() && this.pC(var7)) {
                           var6 = this.ctx
                              .createCallInfo(
                                 DInvokeType.VIRTUAL,
                                 this.dex.addMethod("Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V").getIndex(),
                                 var6.getArguments()
                              );
                           IDInstruction var11 = this.ctx.createInvoke(var6);
                           var11.copyBaseFields(var5);
                           var3.set(1, var11);
                           this.cfg.invalidateDataFlowAnalysis();
                           var1++;
                        }
                     }
                  }
               }
            }
         }

         return var1;
      }
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private boolean pC(IDexMethod var1) {
      Map var2 = (Map)this.ctx.getGlobalContext().getData("map_isSyntheticAddSuppressed", var0 -> new HashMap());
      String var3 = var1.getSignature(false);
      Boolean var4 = (Boolean)var2.get(var3);
      if (var4 != null) {
         return var4;
      } else {
         var4 = false;
         boolean var18 = false /* VF: Semaphore variable */;

         boolean var25;
         label134: {
            label133: {
               label132: {
                  boolean var7;
                  label131: {
                     boolean var13;
                     label130: {
                        label129: {
                           try {
                              var18 = true;
                              if (var1.getData().isConstructor()) {
                                 var25 = false;
                                 var18 = false;
                                 break label134;
                              }

                              if (var1.getData().getCodeItem().getControlFlowGraph().getInstructionCount() >= 20) {
                                 var25 = false;
                                 var18 = false;
                                 break label133;
                              }

                              if (!Boolean.FALSE.equals(((com.pnfsoftware.jeb.corei.parsers.dex.vi)this.dex).Er().pC(var1.getIndex()))) {
                                 var25 = false;
                                 var18 = false;
                                 break label132;
                              }

                              bpx var5 = ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)this.ctx.getGlobalContext().getDecompiler()).pC(var1);
                              var5.A(false);
                              var5.pC(this.ctx.isParseExceptions());
                              var5.pC(this.ctx.getWatchdog());
                              var5.pC(this.ctx.getDecompilationFlags());
                              var5.pC(this.ctx);

                              try {
                                 var5.A();
                              } catch (Exception var19) {
                                 com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var19, var1.getSignature(false));
                                 var7 = false;
                                 var18 = false;
                                 break label131;
                              }

                              IDMasterOptimizer var26 = this.ctx.getGlobalContext().createMasterOptimizer(var5);
                              var26.setSafeMode(true);
                              var26.setPolicyForOptimizerTag("inliner", false);
                              var26.setPolicyForOptimizerTag("slow", false);
                              var26.perform();
                              ((ccp)var26.findOptimizer(ccp.class)).perform(var5);
                              CFG var27 = var5.getCfg();
                              BasicBlock var8 = var27.getBlock(0);
                              if (var8.size() == 2) {
                                 if (((IDInstruction)var8.get(1)).isReturn()) {
                                    if (((IDInstruction)var8.get(0)).isInvoke()) {
                                       if (((IDInstruction)var8.get(0)).getInvokeData() instanceof IDCallInfo) {
                                          IDCallInfo var9 = (IDCallInfo)((IDInstruction)var8.get(0)).getInvokeData();
                                          if (var9.getMethodSignature().equals("Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V")) {
                                             IDExpression var10 = var9.getArgument(0);
                                             IDExpression var11 = var9.getArgument(1);
                                             if (var10 instanceof IDVar) {
                                                if (var11 instanceof IDVar) {
                                                   List var12 = var5.getParameterVariables();
                                                   if (var12.size() == 2) {
                                                      if (var12.get(0) == var10) {
                                                         if (var12.get(1) == var11) {
                                                            var4 = true;
                                                            var13 = true;
                                                            var18 = false;
                                                            break label130;
                                                         }

                                                         var18 = false;
                                                      } else {
                                                         var18 = false;
                                                      }
                                                   } else {
                                                      var18 = false;
                                                   }
                                                } else {
                                                   var18 = false;
                                                }
                                             } else {
                                                var18 = false;
                                             }
                                          } else {
                                             var18 = false;
                                          }
                                       } else {
                                          var18 = false;
                                       }
                                    } else {
                                       var18 = false;
                                    }
                                 } else {
                                    var18 = false;
                                 }
                              } else {
                                 var18 = false;
                              }
                              break label129;
                           } catch (Exception var20) {
                              var18 = false;
                           } finally {
                              if (var18) {
                                 var2.put(var3, var4);
                              }
                           }

                           boolean var6 = false;
                           var2.put(var3, var4);
                           return var6;
                        }

                        boolean var28 = false;
                        var2.put(var3, var4);
                        return var28;
                     }

                     var2.put(var3, var4);
                     return var13;
                  }

                  var2.put(var3, var4);
                  return var7;
               }

               var2.put(var3, var4);
               return var25;
            }

            var2.put(var3, var4);
            return var25;
         }

         var2.put(var3, var4);
         return var25;
      }
   }
}
