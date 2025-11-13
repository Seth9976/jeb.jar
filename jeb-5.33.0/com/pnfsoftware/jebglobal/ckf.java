package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPattern;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPatternVerifier;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeAnalyzerUtil;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBinaryPattern;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEventType;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankX86;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ckf extends cjz {
   private static final ILogger z = GlobalLog.getLogger(ckf.class);
   static BinaryPattern Sc = new BinaryPattern(
      new byte[]{85, -117, -20, 106, -1, 104, 0, 0, 0, 0, 104, 0, 0, 0, 0, 100, -95, 0, 0, 0, 0, 80, 100, -119, 37, 0, 0, 0, 0},
      new byte[]{-1, -1, -1, -1, -1, -1, 0, 0, 0, 0, -1, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
   );
   static BinaryPattern ah = new BinaryPattern(
      new byte[]{85, -117, -20, 106, -2, 104, 0, 0, 0, 0, 104, 0, 0, 0, 0, 100, -95, 0, 0, 0, 0, 80, -125},
      new byte[]{-1, -1, -1, -1, -1, -1, 0, 0, 0, 0, -1, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1}
   );
   static BinaryPattern eP = new BinaryPattern(
      new byte[]{85, -117, -20, 106, -1, 104, 0, 0, 0, 0, 100, -95, 0, 0, 0, 0, 80}, new byte[]{-1, -1, -1, -1, -1, -1, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1}
   );
   static BinaryPattern UO = new BinaryPattern(
      new byte[]{85, -117, -20, -125, -28, -8, 106, -1, 104, 0, 0, 0, 0, 100, -95, 0, 0, 0, 0, 80},
      new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1}
   );
   static BinaryPattern Ab = new BinaryPattern(
      new byte[]{85, -117, -20, 100, -95, 0, 0, 0, 0, 106, -1, 104, 0, 0, 0, 0, 80}, new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, -1}
   );
   static BinaryPatternVerifier rl = new BinaryPatternVerifier();

   public ckf(INativeCodeAnalyzer var1) {
      super(var1);
   }

   @Override
   public boolean pC(boolean var1, boolean var2, boolean var3) throws MemoryException {
      Collection var4 = ((Tw)this.pC.getModel()).sY();
      HashMap var5 = new HashMap();

      for (auu var7 : var4) {
         CFG var8 = var7.E().getCFG();
         if (var8 != null && var8.size() != 0 && var8.getEntryBlock().size() >= 2 && (var7.LM() == null || var7.LM() == 0) && var7.EX() == null) {
            var7.pC(1);
            ckf.Sv var9 = null;
            if (this.pC(var7, 2, "__SEH_prolog")) {
               Long var10 = this.pC((com.pnfsoftware.jeb.corei.parsers.x86.vh)var8.getEntryBlock().get(1));
               if (var10 != null) {
                  var9 = new ckf.Sv(3, false, var10);
               }
            } else if (this.pC(var7, 2, "__SEH_prolog4") || this.pC(var7, 2, "__SEH_prolog4_GS")) {
               Long var45 = this.pC((com.pnfsoftware.jeb.corei.parsers.x86.vh)var8.getEntryBlock().get(1));
               if (var45 != null) {
                  var9 = new ckf.Sv(4, false, var45);
               }
            } else if (this.pC(var7, 1, "__EH_prolog")) {
               Long var43 = this.A((com.pnfsoftware.jeb.corei.parsers.x86.vh)var8.getEntryBlock().get(0));
               if (var43 != null) {
                  Long var11 = this.pC(((Tw)this.pC.getModel()).E(var43));
                  if (var11 != null) {
                     var9 = new ckf.Sv(3, true, var11, var43);
                  }
               }
            } else if (this.pC(var7, 2, "__EH_prolog3")
               || this.pC(var7, 2, "__EH_prolog3_GS")
               || this.pC(var7, 2, "__EH_prolog3_catch")
               || this.pC(var7, 2, "__EH_prolog3_catch_GS")) {
               Long var44 = this.A((com.pnfsoftware.jeb.corei.parsers.x86.vh)var8.getEntryBlock().get(1));
               if (var44 != null) {
                  Long var48 = this.pC(((Tw)this.pC.getModel()).E(var44));
                  if (var48 != null) {
                     var9 = new ckf.Sv(3, true, var48, var44);
                  }
               }
            }

            if (var9 == null && var8.getEntryBlock().size() >= 5) {
               long var46 = var7.E().getMemoryAddress();
               com.pnfsoftware.jeb.corei.parsers.x86.vh var12 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var8.get(0).get(0);
               byte var13 = 0;
               if (var12.toString().equalsIgnoreCase("mov edi, edi")) {
                  var46 += 2L;
                  var13 = 1;
               }

               IBinaryPattern var14 = CodeAnalyzerUtil.checkBinaryPattern(this.pC, rl, var46, var46 + 30L);
               if (var14 != null) {
                  if (var14 == Sc) {
                     Long var15 = this.pC((com.pnfsoftware.jeb.corei.parsers.x86.vh)var8.getEntryBlock().get(3 + var13));
                     if (var15 != null) {
                        var9 = new ckf.Sv(3, false, var15);
                     }
                  } else if (var14 == ah) {
                     Long var60 = this.pC((com.pnfsoftware.jeb.corei.parsers.x86.vh)var8.getEntryBlock().get(3 + var13));
                     if (var60 != null) {
                        var9 = new ckf.Sv(4, false, var60);
                     }
                  } else if (var14 == eP) {
                     Long var61 = this.pC((com.pnfsoftware.jeb.corei.parsers.x86.vh)var8.getEntryBlock().get(3 + var13));
                     if (var61 != null) {
                        Long var16 = this.pC(((Tw)this.pC.getModel()).E(var61));
                        var9 = new ckf.Sv(3, true, var16, var61);
                     }
                  } else if (var14 == Ab || var14 == UO) {
                     Long var62 = this.pC((com.pnfsoftware.jeb.corei.parsers.x86.vh)var8.getEntryBlock().get(4 + var13));
                     if (var62 != null) {
                        Long var65 = this.pC(((Tw)this.pC.getModel()).E(var62));
                        var9 = new ckf.Sv(3, true, var65, var62);
                     }
                  }
               }
            }

            if (var9 != null) {
               var5.put(var7, var9);
            }
         }
      }

      for (Entry var37 : var5.entrySet()) {
         if (((ckf.Sv)var37.getValue()).A && ((ckf.Sv)var37.getValue()).wS != null) {
            auu var40 = ((Tw)this.pC.getModel()).E(((ckf.Sv)var37.getValue()).wS);
            if (var40 == null) {
               cjo.kS(this.pC, ((ckf.Sv)var37.getValue()).wS);
            }
         }
      }

      if (this.pC.needsAnalysis()) {
         this.pC.analyze();
      }

      for (Entry var38 : var5.entrySet()) {
         if (((ckf.Sv)var38.getValue()).kS == null && ((ckf.Sv)var38.getValue()).wS != null && ((ckf.Sv)var38.getValue()).A) {
            ((ckf.Sv)var38.getValue()).kS = this.pC(((Tw)this.pC.getModel()).E(((ckf.Sv)var38.getValue()).wS));
         }
      }

      boolean var36 = ((ZH)this.pC.getModel()).pC(false);

      try {
         for (Entry var41 : var5.entrySet()) {
            auu var42 = (auu)var41.getKey();
            if (!var42.isDisposed()) {
               ckf.Sv var47 = (ckf.Sv)var41.getValue();
               aby var49;
               if (!var47.A) {
                  ckd var50;
                  try {
                     var50 = ckd.pC(this, var47.kS, var2, var47.pC);
                  } catch (MemoryException var31) {
                     continue;
                  }

                  if (var50 == null) {
                     continue;
                  }

                  for (cke var57 : var50.E) {
                     cjo.pC(this.pC, var57.kS);
                     if (!var57.pC()) {
                        cjo.pC(this.pC, var57.A);
                     }
                  }

                  Map var54 = this.pC(var42, var50);
                  if (var54 == null) {
                     continue;
                  }

                  var49 = new aby(abq.pC);

                  for (int var58 = 0; var58 < var50.E.size(); var58++) {
                     cke var63 = (cke)var50.E.get(var58);
                     abw var66;
                     if (var63.pC()) {
                        var66 = abw.pC(var63.kS);
                     } else {
                        var66 = abw.pC(var63.kS, new cjt(var63.A));
                     }

                     ArrayList var17 = new ArrayList();
                     Couple var18 = (Couple)var54.get(var58);
                     if (var18 != null && var18.getFirst() != null && var18.getSecond() != null && !((List)var18.getSecond()).isEmpty()) {
                        for (long var20 : (List)var18.getSecond()) {
                           var17.add(abx.pC((Long)var18.getFirst(), var20, var66));
                        }

                        for (abx var72 : var17) {
                           var49.pC(var72);
                        }
                     }
                  }

                  var42.pC(var49);
               } else {
                  try {
                     cjp var51 = cjp.pC(this, var47.kS, 0L, true);
                     if (var51 == null) {
                        continue;
                     }

                     var51.pC(this.pC);
                     Map var55 = this.pC(var42, var51);
                     if (var55 == null) {
                        continue;
                     }

                     var49 = var51.pC(this.pC, var55);
                     if (var49 != null) {
                        var42.pC(var49);
                     }
                  } catch (MemoryException var32) {
                     continue;
                  }

                  if (var47.wS != null) {
                     auu var52 = ((Tw)this.pC.getModel()).E(var47.wS);
                     if (var52 != null && ((HM)this.pC.getModel().getLabelManager()).pC(var52.getName(true))) {
                        var52.setName(Strings.ff("except_handler_%X", var42.E().getMemoryAddress()));
                     }
                  }
               }

               if (var3 && var49 != null && !var49.kS()) {
                  cjo.pC(this.pC, var42);
               }

               if (var49 != null && !var49.kS()) {
                  for (abx var59 : var49.pC()) {
                     for (abu var67 : var59.kS()) {
                        auu var68 = ((Tw)this.pC.getModel()).E(var67.pC());
                        if (var68 != null) {
                           CFG var69 = var68.E().getCFG();
                           List var71 = var69.getExitBlocks();
                           if (var71.size() == 1) {
                              BasicBlock var73 = (BasicBlock)var71.get(0);
                              if (var73.size() > 2) {
                                 com.pnfsoftware.jeb.corei.parsers.x86.vh var21 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var73.get(var73.size() - 2);
                                 if (var21.getMnemonic().equalsIgnoreCase("mov")
                                    && var21.A()[0].isRegister()
                                    && var21.A()[0].getOperandValue() == RegisterBankX86.getInstance().getDescriptionEntryByName("eax").getId()) {
                                    Long var22 = this.A((com.pnfsoftware.jeb.corei.parsers.x86.vh)var73.get(var73.size() - 2));
                                    if (var22 != null) {
                                       auu var23 = ((Tw)this.pC.getModel()).E(var22);
                                       if (var23 != null) {
                                          boolean var24 = true;

                                          for (IReference var26 : this.pC.getModel().getReferenceManager().getReferencesTo(var23.E().getMemoryAddress())) {
                                             if (var26.getType().isData()
                                                && this.pC
                                                      .getModel()
                                                      .getContainedRoutineAddresses(var26.getFrom().getInternalAddress())
                                                      .stream()
                                                      .filter(var1x -> !var1x.equals(var68.getMemoryAddress()))
                                                      .count()
                                                   != 0L) {
                                                var24 = false;
                                                break;
                                             }
                                          }

                                          if (var24) {
                                             this.pC.enqueuePointerForAnalysis(new CodePointer(var42.E().getMemoryAddress()));
                                             var23.xC();
                                             var42.xC();
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      } finally {
         ((ZH)this.pC.getModel()).pC(var36);
         ((ZH)this.pC.getModel()).notifyListenersOfModelChange(MemoryModelEventType.GENERAL_UPDATE, null);
      }

      if (this.pC.needsAnalysis()) {
         this.pC.analyze();
      }

      return true;
   }

   private Map pC(auu var1, ckd var2) {
      HashMap var3 = new HashMap();
      HashMap var4 = new HashMap();
      int var5 = 0;

      for (cke var7 : var2.E) {
         var4.put(var5, var7.pC);
         Object var8 = (List)var3.get(var7.pC);
         if (var8 == null) {
            var8 = new ArrayList();
            var3.put(var7.pC, var8);
         }

         var8.add(var5);
         var5++;
      }

      ckf.Av var9 = new ckf.Av(var3, var4, var1, var2.pC == 4 ? -2 : -1);
      return !var9.pC() ? null : var9.wS;
   }

   private Map pC(auu var1, cjp var2) {
      HashMap var3 = new HashMap();
      HashMap var4 = new HashMap();
      int var5 = 0;

      for (cjy var7 : var2.pC) {
         var4.put(var5, var7.pC);
         Object var8 = (List)var3.get(var7.pC);
         if (var8 == null) {
            var8 = new ArrayList();
            var3.put(var7.pC, var8);
         }

         var8.add(var5);
         var5++;
      }

      ckf.Av var9 = new ckf.Av(var3, var4, var1, -1);
      return !var9.pC() ? null : var9.wS;
   }

   private boolean pC(auu var1, int var2, String var3) {
      if (var2 >= var1.E().getCFG().getEntryBlock().size()) {
         return false;
      } else {
         IFlowInformation var4 = var1.E().getCFG().getEntryBlock().get2(var2).getRoutineCall();
         if (var4.isBroken() && var4.getTargets().size() == 1 && !((ICodePointer)var4.getTargets().get(0)).isUnknownAddress()) {
            long var5 = ((ICodePointer)var4.getTargets().get(0)).getAddress();
            auu var7 = ((Tw)this.pC.getModel()).E(var5);
            return var7 == null ? false : cjo.pC(var7, var3);
         } else {
            return false;
         }
      }
   }

   private Long pC(com.pnfsoftware.jeb.corei.parsers.x86.vh var1) {
      if (!var1.getMnemonic().equals("push")) {
         return null;
      } else {
         com.pnfsoftware.jeb.corei.parsers.x86.Av[] var2 = var1.A();
         return var2.length != 1 && var2[0].getOperandType() != 1 ? null : var2[0].getOperandValue();
      }
   }

   private Long A(com.pnfsoftware.jeb.corei.parsers.x86.vh var1) {
      if (!var1.getMnemonic().equals("mov")) {
         return null;
      } else {
         com.pnfsoftware.jeb.corei.parsers.x86.Av[] var2 = var1.A();
         return var2.length != 2 && var2[1].getOperandType() != 1 ? null : var2[1].getOperandValue();
      }
   }

   private Long pC(auu var1) {
      if (var1 != null && var1.E() != null) {
         BasicBlock var2 = var1.E().getCFG().getEntryBlock();

         for (int var3 = 0; var3 < 3 && var3 < var1.E().getCFG().size(); var3++) {
            if (var2.size() == 2 && var2.get(1).getMnemonic().equals("jmp")) {
               return this.A((com.pnfsoftware.jeb.corei.parsers.x86.vh)var2.get(0));
            }

            if (var2.outsize() != 1) {
               return null;
            }

            var2 = var2.getOutputBlock(0);
         }
      }

      return null;
   }

   public INativeCodeAnalyzer pC() {
      return this.pC;
   }

   public IVirtualMemory A() {
      return this.wS;
   }

   static {
      rl.addPattern(Sc);
      rl.addPattern(ah);
      rl.addPattern(eP);
      rl.addPattern(UO);
      rl.addPattern(Ab);
   }

   private static class Av {
      final Map pC;
      final Map A;
      final CFG kS;
      final Map wS;
      final Map UT;
      final List E;
      final int sY;

      Av(Map var1, Map var2, auu var3, int var4) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3.E().getCFG();
         this.wS = new HashMap();
         this.sY = var4;
         this.E = new ArrayList();
         this.UT = new HashMap();
      }

      boolean pC() {
         if (!this.pC(this.sY, this.kS.getEntryBlock())) {
            return false;
         } else {
            this.A();
            return true;
         }
      }

      void A() {
         ArrayList var1 = new ArrayList();

         for (Couple var3 : this.wS.values()) {
            if (var3.getSecond() == null) {
               var1.add(var3);
            }
         }

         List var6 = this.kS.getExitBlocks();
         if (!var1.isEmpty() && !var6.isEmpty()) {
            ArrayList var7 = new ArrayList();

            for (BasicBlock var5 : var6) {
               var7.add(var5.getLastAddress());
            }

            for (Couple var9 : var1) {
               var9.setSecond(var7);
            }
         }
      }

      boolean pC(int var1, BasicBlock var2) {
         if (this.E.contains(var2)) {
            return true;
         } else {
            int var3 = var1;

            for (int var4 = 0; var4 < var2.size(); var4++) {
               com.pnfsoftware.jeb.corei.parsers.x86.vh var5 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var2.get(var4);
               long var6 = var2.getAddressOfInstruction(var4);
               if (var5.A().length == 2 && var5.A()[0] instanceof com.pnfsoftware.jeb.corei.parsers.x86.QM) {
                  com.pnfsoftware.jeb.corei.parsers.x86.QM var8 = (com.pnfsoftware.jeb.corei.parsers.x86.QM)var5.A()[0];
                  if (this.pC(var8)) {
                     Integer var9 = null;
                     if (var5.A()[1].getOperandType() == 1) {
                        if (var5.getMnemonic().equals("mov")) {
                           var9 = (int)var5.A()[1].getOperandValue();
                        } else if (var5.getMnemonic().equals("and") && var5.A()[1].getOperandValue() == 0L) {
                           var9 = 0;
                        } else if (var5.getMnemonic().equals("or") && var5.A()[1].getOperandValue() == -1L) {
                           var9 = -1;
                        }
                     }

                     if (var9 == null) {
                        List var10 = (List)this.pC.get(var3);
                        if (var10 != null) {
                           for (Integer var12 : (List)this.pC.get(var3)) {
                              if (!this.wS.containsKey(var12)) {
                                 var9 = var12;
                                 break;
                              }
                           }
                        }

                        if (var9 == null) {
                           var9 = (Integer)this.A.get(var3);
                        }
                     }

                     if (var9 == null) {
                        cjo.pC("SEH state var could not be retrieved");
                        return false;
                     }

                     Integer var16 = (Integer)this.UT.get(var6);
                     if (var16 != null && var16 != var9) {
                        cjo.pC("SEH state var analysis inconsistency");
                        return false;
                     }

                     this.UT.put(var6, var9);
                     Integer var17 = (Integer)this.A.get(var9);
                     if (var3 != this.sY && (var17 == null || var17 != var3)) {
                        Couple var18 = (Couple)this.wS.get(var3);
                        if (var18 == null) {
                           cjo.pC("SEH state var nested states problem");
                           return false;
                        }

                        List var13 = (List)var18.getSecond();
                        if (var13 != null) {
                           var13.add(var6);
                        } else {
                           ArrayList var20 = new ArrayList();
                           var20.add(var6);
                           var18.setSecond(var20);
                        }
                     }

                     if (var9 != this.sY && !this.wS.containsKey(var9)) {
                        Couple var19 = new Couple(var6, null);
                        this.wS.put(var9, var19);
                     }

                     var3 = var9;
                  }
               }
            }

            this.E.add(var2);

            for (BasicBlock var15 : var2.getOutputBlocks()) {
               if (!this.pC(var3, var15)) {
                  return false;
               }
            }

            return true;
         }
      }

      private boolean pC(com.pnfsoftware.jeb.corei.parsers.x86.QM var1) {
         return var1.getMemoryBaseRegister() != -1L
            && com.pnfsoftware.jeb.corei.parsers.x86.MG.UT(var1.getMemoryBaseRegister()).equals("ebp")
            && var1.getMemoryDisplacement() == -4L
            && var1.getMemoryScale() == 0
            && var1.getMemoryIndexRegister() == -1L;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();

         for (Integer var3 : this.wS.keySet()) {
            Couple var4 = (Couple)this.wS.get(var3);
            var1.append(var3);
            var1.append("->");
            Strings.ff(var1, "[%08x->", var4.getFirst());

            for (long var6 : (List)var4.getSecond()) {
               Strings.ff(var1, " %08x", var6);
            }

            var1.append("]");
            var1.append(Strings.LINESEP);
         }

         return var1.toString();
      }
   }

   private static class Sv {
      private final int pC;
      private final boolean A;
      private Long kS;
      private final Long wS;

      public Sv(int var1, boolean var2, Long var3) {
         this(var1, var2, var3, null);
      }

      public Sv(int var1, boolean var2, Long var3, Long var4) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
      }
   }
}
