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
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
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

public class cus extends cum {
   private static final ILogger Gf = GlobalLog.getLogger(cus.class);
   static BinaryPattern Hk = new BinaryPattern(
      new byte[]{85, -117, -20, 106, -1, 104, 0, 0, 0, 0, 104, 0, 0, 0, 0, 100, -95, 0, 0, 0, 0, 80, 100, -119, 37, 0, 0, 0, 0},
      new byte[]{-1, -1, -1, -1, -1, -1, 0, 0, 0, 0, -1, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
   );
   static BinaryPattern Me = new BinaryPattern(
      new byte[]{85, -117, -20, 106, -2, 104, 0, 0, 0, 0, 104, 0, 0, 0, 0, 100, -95, 0, 0, 0, 0, 80, -125},
      new byte[]{-1, -1, -1, -1, -1, -1, 0, 0, 0, 0, -1, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1}
   );
   static BinaryPattern PV = new BinaryPattern(
      new byte[]{85, -117, -20, 106, -1, 104, 0, 0, 0, 0, 100, -95, 0, 0, 0, 0, 80}, new byte[]{-1, -1, -1, -1, -1, -1, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1}
   );
   static BinaryPattern oQ = new BinaryPattern(
      new byte[]{85, -117, -20, -125, -28, -8, 106, -1, 104, 0, 0, 0, 0, 100, -95, 0, 0, 0, 0, 80},
      new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1}
   );
   static BinaryPattern xW = new BinaryPattern(
      new byte[]{85, -117, -20, 100, -95, 0, 0, 0, 0, 106, -1, 104, 0, 0, 0, 0, 80}, new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, -1}
   );
   static BinaryPatternVerifier KT = new BinaryPatternVerifier();

   public cus(INativeCodeAnalyzer var1) {
      super(var1);
   }

   @Override
   public boolean q(boolean var1, boolean var2, boolean var3) throws MemoryException {
      Collection var4 = ((aaf)this.q.getModel()).gP();
      HashMap var5 = new HashMap();

      for (axp var7 : var4) {
         CFG var8 = var7.oW().getCFG();
         if (var8 != null && var8.size() != 0 && var8.getEntryBlock().size() >= 2 && (var7.Rr() == null || var7.Rr() == 0) && var7.TX() == null) {
            var7.q(1);
            cus.CU var9 = null;
            if (this.q(var7, 2, "__SEH_prolog")) {
               Long var10 = this.q((ctc)var8.getEntryBlock().get(1));
               if (var10 != null) {
                  var9 = new cus.CU(3, false, var10);
               }
            } else if (this.q(var7, 2, "__SEH_prolog4") || this.q(var7, 2, "__SEH_prolog4_GS")) {
               Long var45 = this.q((ctc)var8.getEntryBlock().get(1));
               if (var45 != null) {
                  var9 = new cus.CU(4, false, var45);
               }
            } else if (this.q(var7, 1, "__EH_prolog")) {
               Long var43 = this.RF((ctc)var8.getEntryBlock().get(0));
               if (var43 != null) {
                  Long var11 = this.q(((aaf)this.q.getModel()).oW(var43));
                  if (var11 != null) {
                     var9 = new cus.CU(3, true, var11, var43);
                  }
               }
            } else if (this.q(var7, 2, "__EH_prolog3")
               || this.q(var7, 2, "__EH_prolog3_GS")
               || this.q(var7, 2, "__EH_prolog3_catch")
               || this.q(var7, 2, "__EH_prolog3_catch_GS")) {
               Long var44 = this.RF((ctc)var8.getEntryBlock().get(1));
               if (var44 != null) {
                  Long var48 = this.q(((aaf)this.q.getModel()).oW(var44));
                  if (var48 != null) {
                     var9 = new cus.CU(3, true, var48, var44);
                  }
               }
            }

            if (var9 == null && var8.getEntryBlock().size() >= 5) {
               long var46 = var7.oW().getMemoryAddress();
               ctc var12 = (ctc)var8.get(0).get(0);
               byte var13 = 0;
               if (var12.toString().equalsIgnoreCase("mov edi, edi")) {
                  var46 += 2L;
                  var13 = 1;
               }

               IBinaryPattern var14 = CodeAnalyzerUtil.checkBinaryPattern(this.q, KT, var46, var46 + 30L);
               if (var14 != null) {
                  if (var14 == Hk) {
                     Long var15 = this.q((ctc)var8.getEntryBlock().get(3 + var13));
                     if (var15 != null) {
                        var9 = new cus.CU(3, false, var15);
                     }
                  } else if (var14 == Me) {
                     Long var60 = this.q((ctc)var8.getEntryBlock().get(3 + var13));
                     if (var60 != null) {
                        var9 = new cus.CU(4, false, var60);
                     }
                  } else if (var14 == PV) {
                     Long var61 = this.q((ctc)var8.getEntryBlock().get(3 + var13));
                     if (var61 != null) {
                        Long var16 = this.q(((aaf)this.q.getModel()).oW(var61));
                        var9 = new cus.CU(3, true, var16, var61);
                     }
                  } else if (var14 == xW || var14 == oQ) {
                     Long var62 = this.q((ctc)var8.getEntryBlock().get(4 + var13));
                     if (var62 != null) {
                        Long var65 = this.q(((aaf)this.q.getModel()).oW(var62));
                        var9 = new cus.CU(3, true, var65, var62);
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
         if (((cus.CU)var37.getValue()).RF && ((cus.CU)var37.getValue()).Dw != null) {
            axp var40 = ((aaf)this.q.getModel()).oW(((cus.CU)var37.getValue()).Dw);
            if (var40 == null) {
               cub.xK(this.q, ((cus.CU)var37.getValue()).Dw);
            }
         }
      }

      if (this.q.needsAnalysis()) {
         this.q.analyze();
      }

      for (Entry var38 : var5.entrySet()) {
         if (((cus.CU)var38.getValue()).xK == null && ((cus.CU)var38.getValue()).Dw != null && ((cus.CU)var38.getValue()).RF) {
            ((cus.CU)var38.getValue()).xK = this.q(((aaf)this.q.getModel()).oW(((cus.CU)var38.getValue()).Dw));
         }
      }

      boolean var36 = ((Bm)this.q.getModel()).q(false);

      try {
         for (Entry var41 : var5.entrySet()) {
            axp var42 = (axp)var41.getKey();
            if (!var42.isDisposed()) {
               cus.CU var47 = (cus.CU)var41.getValue();
               adq var49;
               if (!var47.RF) {
                  cuq var50;
                  try {
                     var50 = cuq.q(this, var47.xK, var2, var47.q);
                  } catch (MemoryException var31) {
                     continue;
                  }

                  if (var50 == null) {
                     continue;
                  }

                  for (cur var57 : var50.oW) {
                     cub.q(this.q, var57.xK);
                     if (!var57.q()) {
                        cub.q(this.q, var57.RF);
                     }
                  }

                  Map var54 = this.q(var42, var50);
                  if (var54 == null) {
                     continue;
                  }

                  var49 = new adq(adi.q);

                  for (int var58 = 0; var58 < var50.oW.size(); var58++) {
                     cur var63 = (cur)var50.oW.get(var58);
                     ado var66;
                     if (var63.q()) {
                        var66 = ado.q(var63.xK);
                     } else {
                        var66 = ado.q(var63.xK, new cug(var63.RF));
                     }

                     ArrayList var17 = new ArrayList();
                     Couple var18 = (Couple)var54.get(var58);
                     if (var18 != null && var18.getFirst() != null && var18.getSecond() != null && !((List)var18.getSecond()).isEmpty()) {
                        for (long var20 : (List)var18.getSecond()) {
                           var17.add(adp.q((Long)var18.getFirst(), var20, var66));
                        }

                        for (adp var72 : var17) {
                           var49.q(var72);
                        }
                     }
                  }

                  var42.q(var49);
               } else {
                  try {
                     cuc var51 = cuc.q(this, var47.xK, 0L, true);
                     if (var51 == null) {
                        continue;
                     }

                     var51.q(this.q);
                     Map var55 = this.q(var42, var51);
                     if (var55 == null) {
                        continue;
                     }

                     var49 = var51.q(this.q, var55);
                     if (var49 != null) {
                        var42.q(var49);
                     }
                  } catch (MemoryException var32) {
                     continue;
                  }

                  if (var47.Dw != null) {
                     axp var52 = ((aaf)this.q.getModel()).oW(var47.Dw);
                     if (var52 != null && ((Nx)this.q.getModel().getLabelManager()).q(var52.getName(true))) {
                        var52.setName(Strings.ff("except_handler_%X", var42.oW().getMemoryAddress()));
                     }
                  }
               }

               if (var3 && var49 != null && !var49.xK()) {
                  cub.q(this.q, var42);
               }

               if (var49 != null && !var49.xK()) {
                  for (adp var59 : var49.q()) {
                     for (adm var67 : var59.xK()) {
                        axp var68 = ((aaf)this.q.getModel()).oW(var67.q());
                        if (var68 != null) {
                           CFG var69 = var68.oW().getCFG();
                           List var71 = var69.getExitBlocks();
                           if (var71.size() == 1) {
                              BasicBlock var73 = (BasicBlock)var71.get(0);
                              if (var73.size() > 2) {
                                 ctc var21 = (ctc)var73.get(var73.size() - 2);
                                 if (var21.getMnemonic().equalsIgnoreCase("mov")
                                    && var21.Dw()[0].isRegister()
                                    && var21.Dw()[0].getOperandValue() == RegisterBankX86.getInstance().getDescriptionEntryByName("eax").getId()) {
                                    Long var22 = this.RF((ctc)var73.get(var73.size() - 2));
                                    if (var22 != null) {
                                       axp var23 = ((aaf)this.q.getModel()).oW(var22);
                                       if (var23 != null) {
                                          boolean var24 = true;

                                          for (IReference var26 : this.q.getModel().getReferenceManager().getReferencesTo(var23.oW().getMemoryAddress())) {
                                             if (var26.getType().isData()
                                                && this.q
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
                                             this.q.enqueuePointerForAnalysis(new CodePointer(var42.oW().getMemoryAddress()));
                                             var23.PV();
                                             var42.PV();
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
         ((Bm)this.q.getModel()).q(var36);
         ((Bm)this.q.getModel()).notifyListenersOfModelChange(MemoryModelEventType.GENERAL_UPDATE, null);
      }

      if (this.q.needsAnalysis()) {
         this.q.analyze();
      }

      return true;
   }

   private Map q(axp var1, cuq var2) {
      HashMap var3 = new HashMap();
      HashMap var4 = new HashMap();
      int var5 = 0;

      for (cur var7 : var2.oW) {
         var4.put(var5, var7.q);
         Object var8 = (List)var3.get(var7.q);
         if (var8 == null) {
            var8 = new ArrayList();
            var3.put(var7.q, var8);
         }

         var8.add(var5);
         var5++;
      }

      cus.eo var9 = new cus.eo(var3, var4, var1, var2.q == 4 ? -2 : -1);
      return !var9.q() ? null : var9.Dw;
   }

   private Map q(axp var1, cuc var2) {
      HashMap var3 = new HashMap();
      HashMap var4 = new HashMap();
      int var5 = 0;

      for (cul var7 : var2.q) {
         var4.put(var5, var7.q);
         Object var8 = (List)var3.get(var7.q);
         if (var8 == null) {
            var8 = new ArrayList();
            var3.put(var7.q, var8);
         }

         var8.add(var5);
         var5++;
      }

      cus.eo var9 = new cus.eo(var3, var4, var1, -1);
      return !var9.q() ? null : var9.Dw;
   }

   private boolean q(axp var1, int var2, String var3) {
      if (var2 >= var1.oW().getCFG().getEntryBlock().size()) {
         return false;
      } else {
         IFlowInformation var4 = var1.oW().getCFG().getEntryBlock().get2(var2).getRoutineCall();
         if (var4.isBroken() && var4.getTargets().size() == 1 && !((ICodePointer)var4.getTargets().get(0)).isUnknownAddress()) {
            long var5 = ((ICodePointer)var4.getTargets().get(0)).getAddress();
            axp var7 = ((aaf)this.q.getModel()).oW(var5);
            return var7 == null ? false : cub.q(var7, var3);
         } else {
            return false;
         }
      }
   }

   private Long q(ctc var1) {
      if (!var1.getMnemonic().equals("push")) {
         return null;
      } else {
         cqj[] var2 = var1.Dw();
         return var2.length != 1 && var2[0].getOperandType() != 1 ? null : var2[0].getOperandValue();
      }
   }

   private Long RF(ctc var1) {
      if (!var1.getMnemonic().equals("mov")) {
         return null;
      } else {
         cqj[] var2 = var1.Dw();
         return var2.length != 2 && var2[1].getOperandType() != 1 ? null : var2[1].getOperandValue();
      }
   }

   private Long q(axp var1) {
      if (var1 != null && var1.oW() != null) {
         BasicBlock var2 = var1.oW().getCFG().getEntryBlock();

         for (int var3 = 0; var3 < 3 && var3 < var1.oW().getCFG().size(); var3++) {
            if (var2.size() == 2 && var2.get(1).getMnemonic().equals("jmp")) {
               return this.RF((ctc)var2.get(0));
            }

            if (var2.outsize() != 1) {
               return null;
            }

            var2 = var2.getOutputBlock(0);
         }
      }

      return null;
   }

   public ITypeManager q() {
      return this.RF;
   }

   public INativeCodeAnalyzer RF() {
      return this.q;
   }

   public IVirtualMemory xK() {
      return this.Dw;
   }

   static {
      KT.addPattern(Hk);
      KT.addPattern(Me);
      KT.addPattern(PV);
      KT.addPattern(oQ);
      KT.addPattern(xW);
   }

   private static class CU {
      private final int q;
      private final boolean RF;
      private Long xK;
      private final Long Dw;

      public CU(int var1, boolean var2, Long var3) {
         this(var1, var2, var3, null);
      }

      public CU(int var1, boolean var2, Long var3, Long var4) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
      }
   }

   private static class eo {
      final Map q;
      final Map RF;
      final CFG xK;
      final Map Dw;
      final Map Uv;
      final List oW;
      final int gO;

      eo(Map var1, Map var2, axp var3, int var4) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3.oW().getCFG();
         this.Dw = new HashMap();
         this.gO = var4;
         this.oW = new ArrayList();
         this.Uv = new HashMap();
      }

      boolean q() {
         if (!this.q(this.gO, this.xK.getEntryBlock())) {
            return false;
         } else {
            this.RF();
            return true;
         }
      }

      void RF() {
         ArrayList var1 = new ArrayList();

         for (Couple var3 : this.Dw.values()) {
            if (var3.getSecond() == null) {
               var1.add(var3);
            }
         }

         List var6 = this.xK.getExitBlocks();
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

      boolean q(int var1, BasicBlock var2) {
         if (this.oW.contains(var2)) {
            return true;
         } else {
            int var3 = var1;

            for (int var4 = 0; var4 < var2.size(); var4++) {
               ctc var5 = (ctc)var2.get(var4);
               long var6 = var2.getAddressOfInstruction(var4);
               if (var5.Dw().length == 2 && var5.Dw()[0] instanceof ctd) {
                  ctd var8 = (ctd)var5.Dw()[0];
                  if (this.q(var8)) {
                     Integer var9 = null;
                     if (var5.Dw()[1].getOperandType() == 1) {
                        if (var5.getMnemonic().equals("mov")) {
                           var9 = (int)var5.Dw()[1].getOperandValue();
                        } else if (var5.getMnemonic().equals("and") && var5.Dw()[1].getOperandValue() == 0L) {
                           var9 = 0;
                        } else if (var5.getMnemonic().equals("or") && var5.Dw()[1].getOperandValue() == -1L) {
                           var9 = -1;
                        }
                     }

                     if (var9 == null) {
                        List var10 = (List)this.q.get(var3);
                        if (var10 != null) {
                           for (Integer var12 : (List)this.q.get(var3)) {
                              if (!this.Dw.containsKey(var12)) {
                                 var9 = var12;
                                 break;
                              }
                           }
                        }

                        if (var9 == null) {
                           var9 = (Integer)this.RF.get(var3);
                        }
                     }

                     if (var9 == null) {
                        cub.q("SEH state var could not be retrieved");
                        return false;
                     }

                     Integer var16 = (Integer)this.Uv.get(var6);
                     if (var16 != null && var16 != var9) {
                        cub.q("SEH state var analysis inconsistency");
                        return false;
                     }

                     this.Uv.put(var6, var9);
                     Integer var17 = (Integer)this.RF.get(var9);
                     if (var3 != this.gO && (var17 == null || var17 != var3)) {
                        Couple var18 = (Couple)this.Dw.get(var3);
                        if (var18 == null) {
                           cub.q("SEH state var nested states problem");
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

                     if (var9 != this.gO && !this.Dw.containsKey(var9)) {
                        Couple var19 = new Couple(var6, null);
                        this.Dw.put(var9, var19);
                     }

                     var3 = var9;
                  }
               }
            }

            this.oW.add(var2);

            for (BasicBlock var15 : var2.getOutputBlocks()) {
               if (!this.q(var3, var15)) {
                  return false;
               }
            }

            return true;
         }
      }

      private boolean q(ctd var1) {
         return var1.getMemoryBaseRegister() != -1L
            && ctf.Uv(var1.getMemoryBaseRegister()).equals("ebp")
            && var1.getMemoryDisplacement() == -4L
            && var1.getMemoryScale() == 0
            && var1.getMemoryIndexRegister() == -1L;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();

         for (Integer var3 : this.Dw.keySet()) {
            Couple var4 = (Couple)this.Dw.get(var3);
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
}
