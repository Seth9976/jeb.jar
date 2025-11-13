package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.InstructionFlags;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Kt {
   private static final ILogger pC = GlobalLog.getLogger(Kt.class);
   private final a A;
   private MultiMap kS = new MultiMap();
   private Map wS = new HashMap();
   private List UT = new ArrayList();

   public Kt(INativeCodeAnalyzer var1) {
      this.A = (a)var1;
   }

   public boolean pC(long var1) {
      Kt.Av var3 = (Kt.Av)this.wS.get(var1);
      return var3 != null && var3.pC();
   }

   private Boolean pC(INativeMethodDataItem var1) {
      Kt.Av var2 = (Kt.Av)this.wS.get(var1.getMemoryAddress());
      if (var2 == null) {
         return null;
      } else {
         return var2.A(var1) ? (Boolean)var2.A.get(var2.kS) : null;
      }
   }

   public void pC() {
      this.kS = new MultiMap();
      this.wS = new HashMap();
   }

   public boolean pC(List var1) {
      ArrayList var2 = new ArrayList();

      for (aut var4 : var1) {
         this.pC(var1, var4, var2);
      }

      this.A(var2);
      return !var2.isEmpty();
   }

   public void A(List var1) {
      this.A.ys().pC.verifyLocked();

      for (aut var3 : var1) {
         this.pC(var3);
      }

      if (!this.UT.isEmpty() && this.A.xC() != null) {
         this.A.pC(true, true);

         for (Long var6 : this.UT) {
            INativeContinuousItem var4 = this.A.ys().getItemAt(var6);
            if (var4 instanceof INativeInstructionItem) {
               this.A.ys().pC(var4, true);
               this.pC(var4);
            }
         }

         this.UT = new ArrayList();
      }
   }

   private void pC(INativeContinuousItem var1) {
      Set var2 = this.A.ys().gp().getReferencesTo(var1.getMemoryAddress());
      if (var2 != null && !var2.isEmpty()) {
         for (xy var4 : var2) {
            ArrayList var5 = new ArrayList();
            long var6 = var4.getFrom().getInternalAddress();
            INativeContinuousItem var8 = this.A.ys().getItemAt(var6);
            if (var8 != null && var8 instanceof INativeInstructionItem) {
               IInstruction var9 = ((INativeInstructionItem)var8).getInstruction();
               this.A.UT().determinePotentialPointers(var6, var9, var5);

               for (Pointer var11 : var5) {
                  if (((var11.getType() & 2) != 0 || var11.getType() == 5) && var11.getAddress() == var1.getMemoryAddress()) {
                     Object[] var10000 = new Object[]{var1.getMemoryAddress()};
                     this.A.enqueuePointerForAnalysis(var11, 0);
                     return;
                  }
               }
            }
         }
      }
   }

   private Boolean pC(List var1, aut var2, List var3) {
      return this.pC(var1, var2, var3, new ArrayList());
   }

   private Boolean pC(List var1, aut var2, List var3, List var4) {
      this.A.ys().pC.verifyLocked();
      if (var2.kS() != null && !var2.isDisposed()) {
         Boolean var5 = var2.kS().getNonReturning();
         if (var5 != null) {
            return var5;
         } else {
            boolean var6 = false;
            boolean var7 = false;
            Kt.Av var8 = (Kt.Av)this.wS.get(var2.getMemoryAddress());
            if (var8 != null && var8.A(var2) && var8.pC()) {
               this.A(var2, true);
               return true;
            } else {
               if (!var7) {
                  auu var9 = var2.E();
                  if (var9 != null) {
                     Boolean var10 = var9.getNonReturning();
                     if (var10 == null && !var4.contains(var2.getMemoryAddress())) {
                        if (var9.E() != null) {
                           var4.add(var2.getMemoryAddress());
                           this.pC(var1, var9.E(), var3, var4);
                           var4.remove(var4.size() - 1);
                           var10 = var9.getNonReturning();
                        } else if (var9.Cu() != null) {
                           aut var11 = this.A.ys().wS(var9.Cu());
                           if (var11 != null) {
                              var10 = var11.kS().getNonReturning();
                              if (var10 == null) {
                                 var4.add(var2.getMemoryAddress());
                                 this.pC(var1, var11, var3, var4);
                                 var4.remove(var4.size() - 1);
                                 var10 = var11.kS().getNonReturning();
                              }
                           }
                        }
                     }

                     if (var10 != null && var10) {
                        var6 = this.A(var2, true);
                        var7 = true;
                     }
                  }
               }

               if (!var7) {
                  ChainedOperationResult var21 = this.A.UT().isNonReturningRoutine(var2.kS());
                  if (!var21.getContinuationStatus().equals(ChainedOperationResult.ContinuationStatus.IGNORE)) {
                     var6 = this.A(var2, (Boolean)var21.getResult());
                     var7 = true;
                  }
               }

               if (!var7) {
                  CFG var22 = var2.getCFG();
                  List var23 = var22.getExitBlocks();
                  boolean var24 = true;
                  HashSet var12 = new HashSet();

                  for (BasicBlock var14 : var23) {
                     boolean var15 = false;
                     if (!var15) {
                        for (INativeMethodItem var18 : this.A
                           .ys()
                           .getCallGraphManager()
                           .getGlobalCallGraph()
                           .getCalleeRoutines(var14.getLastAddress(), !this.A.wS().xC())) {
                           if (Booleans.isTrue(((auu)var18).getNonReturning())) {
                              var15 = true;
                              break;
                           }

                           INativeMethodDataItem var19 = var18.getData();
                           if (var19 != null) {
                              if (var19.getMemoryAddress() == var2.getMemoryAddress()) {
                                 var15 = true;
                                 break;
                              }

                              Boolean var20 = this.pC(var19);
                              if (var20 == null) {
                                 if (var1.contains(var19)) {
                                    var12.add(var19);
                                    var15 = true;
                                    break;
                                 }
                              } else if (var20) {
                                 var15 = true;
                                 break;
                              }
                           }
                        }
                     }

                     if (!var15 && var14.getLast().getInstructionFlags().contains(InstructionFlags.INTERRUPT_EXEC)) {
                        var15 = true;
                     }

                     if (!var15) {
                        var24 = false;
                        break;
                     }
                  }

                  if (var24) {
                     boolean var25 = false;
                     if (!var12.isEmpty()) {
                        for (INativeMethodDataItem var27 : var12) {
                           if (var4.contains(var27.getMemoryAddress())) {
                              var25 = true;
                           } else {
                              var4.add(var2.getMemoryAddress());
                              boolean var28 = this.pC(var1, (aut)var27, var3, var4);
                              if (!var28) {
                                 var24 = false;
                                 break;
                              }

                              var4.remove(var4.size() - 1);
                           }
                        }
                     }

                     if (var24) {
                        if (var25) {
                           return var24;
                        }

                        var6 = this.A(var2, true);
                     }
                  }
               }

               if (var6) {
                  if (var8 == null || !var8.pC()) {
                     var3.add(var2);
                  }

                  this.pC(var2, var6);
               } else {
                  if (var8 != null && var8.pC()) {
                     var3.add(var2);
                  }

                  this.pC(var2, var6);
               }

               return var6;
            }
         }
      } else {
         return null;
      }
   }

   private void pC(aut var1, boolean var2) {
      Kt.Av var3 = (Kt.Av)this.wS.get(var1.getMemoryAddress());
      if (var3 == null) {
         var3 = new Kt.Av(var1, var2);
         this.wS.put(var1.getMemoryAddress(), var3);
      } else {
         var3.A.put(var3.pC(var1), var2);
      }
   }

   private boolean A(aut var1, boolean var2) {
      Boolean var3 = var1.kS().getNonReturning();
      if (var3 != null && var3 == var2) {
         return var2;
      } else {
         var1.kS().wS(var2);
         return var2;
      }
   }

   private void pC(aut var1) {
      HashSet var2 = new HashSet();

      for (Long var4 : ((bj)this.A.ys().getCallGraphManager().getGlobalCallGraph()).A(var1, !this.A.wS().xC())) {
         for (Long var6 : this.A.ys().getContainedRoutineAddresses(var4)) {
            auu var7 = this.A.ys().E(var6);
            if (var7 != null && !var7.isDisposed()) {
               INativeContinuousItem var8 = this.A.ys().getItemAt(var4);
               if (var8 instanceof INativeInstructionItem) {
                  List var9 = this.kS.get(var7.E().getMemoryAddress());
                  if (var9 == null || !var9.contains(var1.getMemoryAddress())) {
                     var2.add(CodePointer.createFrom(var7));
                     this.kS.put(var7.E().getMemoryAddress(), var1.getMemoryAddress());
                  }

                  CFG var10 = var7.E().getCFG();
                  BasicBlock var11 = var10.getBlockAt((Long)var8.getEnd());
                  if (var11 != null) {
                     HashSet var12 = new HashSet();
                     ArrayList var13 = new ArrayList();
                     var13.add(var11);
                     int var14 = 0;

                     while (!var13.isEmpty()) {
                        BasicBlock var15 = (BasicBlock)var13.remove(0);
                        var12.add(var15);

                        for (BasicBlock var17 : var15.getAllOutputs()) {
                           if (!var12.contains(var17) && !var13.contains(var17)) {
                              var13.add(var17);
                           }
                        }

                        if (var14++ == 100) {
                           break;
                        }
                     }

                     for (BasicBlock var29 : var12) {
                        for (long var20 : var29.getAddresses()) {
                           this.UT.add(var20);

                           for (Long var24 : this.A.ys().getContainedRoutineAddresses(var20)) {
                              INativeContinuousItem var25 = this.A.ys().getItemAt(var24);
                              if (var25 instanceof INativeInstructionItem) {
                                 var2.add(new CodePointer(var24, ((INativeInstructionItem)var25).getInstruction().getProcessorMode()));
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      for (CodePointer var27 : var2) {
         this.A.enqueuePointerForAnalysis(var27, 1, 32);
      }
   }

   private class Av {
      private final Map A;
      private String kS = null;

      public Av(INativeMethodDataItem var2, boolean var3) {
         this.A = new HashMap();
         String var4 = this.pC(var2);
         this.A.put(var4, var3);
         this.kS = var4;
      }

      private String pC(INativeMethodDataItem var1) {
         CFG var2 = var1.getCFG();
         List var3 = var2.getExitBlocks();
         StringBuilder var4 = new StringBuilder();

         for (BasicBlock var6 : var3) {
            var4.append(var6.getBase()).append(':').append(var6.getSizeOfInstructions()).append(';');
         }

         this.kS = var4.toString();
         return this.kS;
      }

      private boolean A(INativeMethodDataItem var1) {
         this.kS = this.pC(var1);
         Boolean var2 = (Boolean)this.A.get(this.kS);
         return var2 != null;
      }

      private boolean pC() {
         Boolean var1 = (Boolean)this.A.get(this.kS);
         return Boolean.TRUE.equals(var1);
      }
   }
}
