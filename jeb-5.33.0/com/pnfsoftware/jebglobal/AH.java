package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.PointerLocation;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataStringUtil;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.InstructionUtil;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Characters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

public class AH {
   private static final ILogger A = GlobalLog.getLogger(AH.class);
   public static boolean pC = true;
   private static final Set kS = Sets.newHashSet(0L);
   private INativeCodeAnalyzer wS;
   private dA UT;
   private ZM E;
   private Map sY = new HashMap();
   private Map ys = new HashMap();
   private AH.Av ld = new AH.Av();

   public AH(INativeCodeAnalyzer var1, com.pnfsoftware.jeb.corei.parsers.arm.Av var2) {
      this.wS = var1;
      this.UT = new dA(var2);
      this.E = new ZM(var1, var2, true, true);
   }

   public void pC() {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.sY.entrySet()) {
         this.pC(var1, (mP)var3.getValue());
         this.pC(var1);
      }

      for (PointerLocation var5 : var1) {
         ((a)this.wS).pC(var5, true);
      }

      if (this.wS.needsAnalysis()) {
         this.wS.analyze();
      }

      this.sY = null;
      this.sY = new HashMap();
      this.ys = null;
      this.ys = new HashMap();
   }

   public void pC(IBasicBlockSkeleton var1, List var2) {
      mP var3 = (mP)this.sY.get(var1.getFirstAddress());
      if (var3 == null) {
         var3 = new mP();
      } else {
         this.sY.remove(var1.getFirstAddress());
      }

      long var4 = var1.getFirstAddress();
      HashMap var6 = new HashMap();

      for (int var7 = 0; var7 < var1.getInsntructions().size(); var7++) {
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var8 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.getInsntructions().get(var7);
         Yg[] var9 = var8.A();
         String var10 = var8.wS().pC();
         if (!var3.pC() && var8.wS().kS()) {
            var3.A();
         }

         byte var12 = -1;
         switch (var10.hashCode()) {
            case 64655:
               if (var10.equals("ADR")) {
                  var12 = 0;
               }
               break;
            case 75226:
               if (var10.equals("LDR")) {
                  var12 = 3;
               }
               break;
            case 76532:
               if (var10.equals("MOV")) {
                  var12 = 2;
               }
               break;
            case 2004385:
               if (var10.equals("ADRP")) {
                  var12 = 1;
               }
         }

         label271: {
            switch (var12) {
               case 0:
                  var3.pC(var8.wS(), var9[0], var9[1].getOperandValue(var4), var4, mP.Av.pC);
                  break label271;
               case 1:
                  var3.pC(var8.wS(), var9[0], var9[1].getOperandValue(var4), var4, mP.Av.A);
                  break label271;
               case 2:
                  if (!var9[1].equals(var9[0])) {
                     if (var3.pC(var9[0])) {
                        this.pC(var2, var3, var9[0]);
                     }

                     if (var9[1].getOperandType() == 1) {
                        var3.pC(var8.wS(), var9[0], var9[1].getOperandValue(var4), var4, mP.Av.wS);
                     } else if (!var3.pC() && var9.length == 2 && var9[1].getOperandType() == 0 && var3.pC(var9[1])) {
                        var3.pC(var8.wS(), var9[0], var4, var9[1], null, var8.getProcessorMode());
                     }
                  }
                  break label271;
               case 3:
                  Yg var13 = (Yg)var9[1].merge(var4);
                  var13 = var13 != null ? var13 : var9[1];
                  if (InstructionUtil.isAddressOperand(var13)) {
                     if (var9[0].A(var8.getProcessorMode())) {
                        this.pC(var2, var4, var9[1].getOperandValue(var4), 4, 5, 0);
                     } else {
                        try {
                           long var14 = this.wS.getMemory().readPointer(var13.getOperandValue(var4));
                           var3.pC(var8.wS(), var9[0], var14, var4, mP.Av.kS);
                           var3.A(var9[0]);
                           var6.put(var4, Maps.toMap(mP.UT(var9[0]), var13.getOperandValue(var4)));
                           break label271;
                        } catch (MemoryException var26) {
                        }
                     }
                  }
                  break;
               default:
                  if (var3.pC()) {
                     break label271;
                  }
            }

            Yg var11 = var8.A() == null ? null : (var8.A().length > 0 ? var8.A()[0] : null);
            Yg var31 = var8.A() == null ? null : (var8.A().length > 1 ? var8.A()[1] : null);
            Yg var33 = var8.A() == null ? null : (var8.A().length > 2 ? var8.A()[2] : null);
            Iterator var17;
            switch (var10) {
               case "ADD":
               case "SUB":
               case "ORR":
                  if (var8.A().length == 2) {
                     var33 = var31;
                     var31 = var11;
                  }

                  this.pC(var3, var31, var33, var6, var8.getProcessorMode());
                  boolean var37 = var3.pC(var8.wS(), var11, var4, var31, var33, var8.getProcessorMode());
                  if (!var37) {
                     this.pC(var2, var3, var11);
                  }
                  break label271;
               case "LD1":
               case "LD2":
               case "LD3":
               case "LD4":
               case "ST1":
               case "ST2":
               case "ST3":
               case "ST4":
                  KH var36 = (KH)var31;
                  if (var3.pC(var36.kS()) && var11.getOperandType() == 7) {
                     int var39 = 4;
                     Set var40 = var3.A(var36.kS());
                     int var19 = 0;

                     label243:
                     for (Long var21 : var40) {
                        int var22 = 0;

                        byte var23;
                        while (true) {
                           if (var22 >= var11.E().length) {
                              continue label243;
                           }

                           var23 = 2;
                           if (!(var11.E()[var22] instanceof lw)) {
                              break;
                           }

                           int var24 = u.wS((lw)var11.E()[var22]);
                           var39 = u.kS((lw)var11.E()[var22]);
                           if (var39 == -1) {
                              var39 = 4;
                              break;
                           }

                           for (int var25 = 0; var25 < var24; var25++) {
                              this.pC(var4, var21, new AH.Av((long)var19), var39, var23, var2);
                              var19 += var39;
                           }

                           var22++;
                        }

                        this.pC(var4, var21, new AH.Av((long)var19), var39, var23, var2);
                     }

                     if (var10.startsWith("L")) {
                        this.pC(var2, var3, var11.E());
                     }

                     if (var33 != null) {
                        this.pC(var2, var3, var31);
                     }
                     break label271;
                  }

                  this.pC(var2, var3, var9);
                  break label271;
               case "BLX":
               case "BX":
               case "BR":
               case "BLR":
                  if (var3.pC(var11)) {
                     this.pC(var2, var4, var3.A(var11));
                  }

                  if (var8.pC().getBranchType() == IInsnEmulator.BranchType.CALL) {
                     Yg var35 = LC.ys(this.wS.getProcessor().getMode());
                     var3.wS(var35);
                  }
                  break label271;
               case "RET":
                  Yg var34 = var11 == null ? LC.ys(this.wS.getProcessor().getMode()) : var11;
                  if (var3.pC(var34)) {
                     this.pC(var2, var4, var3.A(var34));
                  }
                  break label271;
               default:
                  List var16 = this.pC(var8, var10);
                  var17 = var16.iterator();
            }

            while (var17.hasNext()) {
               Yg var18 = (Yg)var17.next();
               this.pC(var3, var18, var6);
               var3.A(var18);
            }

            mN var38 = MX.pC(var8);
            if (var38 != null && var38.Sc()) {
               this.pC(var3, var2, var4, var8.wS(), var38, var6, var8.getProcessorMode());
            } else {
               this.pC(var2, var3, var9);
            }
         }

         var4 += ((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.getInsntructions().get(var7)).getSize();
      }

      Long var27 = null;
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var28 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.getInsntructions()
         .get(var1.getInsntructions().size() - 1);
      long var29 = var4 - var28.getSize();
      IFlowInformation var30 = var28.getBreakingFlow(var29);
      if (var30.isBrokenKnown() && !var28.UT().E()) {
         var27 = var29 + var28.getSize();
      }

      if (var27 != null && this.wS.getModel().isBasicBlockHeader(var27)) {
         var27 = null;
      }

      if (var27 != null) {
         this.sY.put(var27, var3);
      } else {
         this.pC(var2, var3);
         this.pC(var2);
      }
   }

   private List pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, String var2) {
      if (Strings.startsWith(var2, "MUL", "SMU", "UMUL", "SMLAL", "SMMUL", "UMAAL", "UMLAL", "MNEG", "SMNEG", "UMNEG")) {
         return Arrays.asList(var1.pC(var1.getCountOfOperands() - 2), var1.pC(var1.getCountOfOperands() - 1));
      } else {
         return (List)(Strings.startsWith(var2, "MLA", "MLS", "SML", "SMML", "MADD", "MSUB", "SMADD", "SMSUB", "UMADD", "UMSUB")
            ? Arrays.asList(var1.pC(var1.getCountOfOperands() - 3), var1.pC(var1.getCountOfOperands() - 2))
            : new ArrayList());
      }
   }

   private void pC(mP var1, Yg var2, Yg var3, Map var4, int var5) {
      boolean var6 = var1.pC(var2, var5);
      if (var6 || var1.pC(var3, var5)) {
         Yg var7 = var6 ? var3 : var2;
         this.pC(var1, var7, var4);
      }
   }

   private void pC(mP var1, Yg var2, Map var3) {
      long var4 = mP.UT(var2);
      Long var6 = var1.kS(var4);
      if (var6 != null) {
         Map var7 = (Map)var3.get(var6);
         if (var7 != null) {
            Long var8 = (Long)var7.get(var4);
            if (var8 != null) {
               this.UT.pC(var8, var6);
            }
         }
      }
   }

   public void pC(mP var1, List var2, long var3, NC var5, mN var6, Map var7, int var8) {
      Set var9 = null;
      Set var10 = null;
      Object var11 = null;
      int var12 = var6.UT() / 8;
      boolean var13 = false;
      if (InstructionUtil.isAddressOperand(var6.wS())) {
         var13 = true;
      } else {
         Yg var14 = var6.wS().pC(var3);
         if (var14 != null && InstructionUtil.isAddressOperand(var14)) {
            var13 = true;
         }
      }

      KH var26 = null;
      Yg var15 = null;
      if (!var13) {
         var26 = var6.wS();
         var15 = var26.kS();
      }

      boolean var16 = false;
      if (!var13 && var26 != null) {
         if (var1.pC(var15)) {
            var16 = true;
         } else if (var15.A(var8) && var26.sY() != null && var26.sY().getOperandType() == 0 && var1.pC(var26.sY())) {
            var16 = true;
         }
      }

      int var17 = var6.oT() == mN.Av.UT ? 1 : var6.kS();
      if (var16) {
         Yg var18 = var26.sY();
         if (var18 == null) {
            var10 = kS;
            var11 = kS;
         } else if (var18.getOperandType() == 1 || var18.getOperandType() == 0 && (var1.pC(var18) || var26.ld() && !var26.ys())) {
            var10 = var1.pC(var18, var3, var8);
            var11 = var26.ys() ? var10 : kS;
         }

         var9 = var1.pC(var15, var3, var8);
         var1.A(var15);
         var1.A(var18);
         if (var18 != null) {
            this.pC(var1, var15, var18, var7, var8);
         }

         byte var19 = 0;
         int var20 = var12;
         if (var6.A(0).A(var8)) {
            var19 = 5;
         } else if (var8 == 64) {
            if (var12 != 8) {
               var19 = 2;
            }

            if (var5.E().equals("STP") && (var6.A(0).E(var8) && var6.A(1).E(var8) || var6.A(0).equals(var6.A(1)))) {
               var20 = -1;
            }
         } else if (var12 != 4) {
            var19 = 2;
         }

         for (Long var22 : var9) {
            if (!this.UT.A(var22)) {
               if (var11 == null) {
                  for (int var41 = 0; var41 < var17; var41++) {
                     this.pC(var3, var22 + var41 * var12, new AH.Av(var18), var20, var19, var2);
                  }
               } else {
                  if (var17 > 1) {
                     var11 = new TreeSet((Collection)var11);
                     Long var23 = (Long)var11.iterator().next();

                     for (int var24 = 1; var24 < var17; var24++) {
                        Long var25 = var23 + var12 * var24;
                        if (!var11.contains(var25)) {
                           var11.add(var25);
                        }
                     }
                  }

                  for (Long var43 : var11) {
                     this.pC(var3, var22, new AH.Av(var43), var20, var19, var2);
                  }
               }
            }
         }
      }

      if (var26 != null && var26.UT()) {
         if (var9 != null && var10 != null) {
            ArrayList var27 = new ArrayList();
            if (var17 <= 1 && var26.sY() != null) {
               for (Long var33 : var9) {
                  for (Long var38 : var10) {
                     var27.add(var33 + var38);
                  }
               }
            } else {
               int var29 = var17 * var12;

               for (Long var35 : var9) {
                  var27.add(var35 + var29);
               }
            }

            var1.pC(var5, var15, var27, var3, mP.Av.wS);
         } else {
            this.pC(var2, var1, var6.wS());
         }
      }

      if (var6.ys()) {
         if (var11 != null) {
            for (int var28 = 0; var28 < var6.kS(); var28++) {
               ArrayList var31 = new ArrayList();
               int var34 = var28 * var12;

               for (Long var39 : var9) {
                  for (Long var44 : var11) {
                     Long var45 = VirtualMemoryUtil.readAsLongSafe(this.wS.getMemory(), var39 + var44 + var34, var12);
                     if (var45 != null && this.wS.getAnalysisRanges().contains(var45)) {
                        var31.add(var45 + var12 * var28);
                     }
                  }
               }

               if (!var31.isEmpty()) {
                  var1.pC(var5, var6.A(var28), var31, var3, mP.Av.kS);
                  var1.A(var6.A(var28));
                  var7.put(var3, Maps.toMap(mP.UT(var6.A(var28)), (Long)var9.iterator().next()));
               } else {
                  var1.wS(var6.A(var28));
               }
            }
         } else {
            this.pC(var2, var1, var6.ld());
         }
      }

      if (var6.sY() != null) {
         this.pC(var2, var1, var6.sY());
         var1.pC(var5, var6.sY(), 0L, var3, mP.Av.wS);
      }
   }

   private void pC(List var1, mP var2, Yg[] var3) {
      for (Couple var6 : var2.pC(var3)) {
         for (Long var8 : (Set)var6.getSecond()) {
            this.pC(var1, (Long)var6.getFirst(), var8.longValue());
         }
      }
   }

   private void pC(List var1, mP var2) {
      for (Couple var5 : var2.pC(this.wS)) {
         for (Long var7 : (Set)var5.getSecond()) {
            this.pC(var1, (Long)var5.getFirst(), var7.longValue());
         }
      }
   }

   private void pC(List var1, mP var2, Yg var3) {
      List var4 = var2.wS(var3);
      if (var4 != null) {
         for (Couple var6 : var4) {
            for (Long var8 : (Set)var6.getSecond()) {
               this.pC(var1, (Long)var6.getFirst(), var8.longValue());
            }
         }
      }
   }

   private void pC(List var1, long var2, long var4) {
      this.pC(var2, var4, this.ld, -1, 0, var1);
   }

   private void pC(List var1, long var2, Set var4) {
      int var5 = this.wS.getProcessor().getMode() == 64 ? 8 : 4;

      for (Long var7 : var4) {
         this.pC(var1, var2, var7, var5, 1, 0);
      }
   }

   private void pC(List var1, long var2, Long var4) {
      this.pC(var1, var2, var4, 0, 1, 0);
   }

   private Pointer pC(List var1, long var2, long var4, int var6, int var7, int var8) {
      if (this.wS.getAnalysisRanges().contains(var4)) {
         Object var9 = var7 == 1 ? this.wS.getProcessor().createEntryPoint(var4) : new Pointer(var4, var6, var7);
         var1.add(new PointerLocation(var2, (Pointer)var9, var8));
         return (Pointer)var9;
      } else {
         return null;
      }
   }

   private void pC(long var1, long var3, AH.Av var5, int var6, int var7, List var8) {
      long var9 = var3;
      Pointer var11 = null;
      if (var5.pC != null) {
         var9 = var3 + var5.pC;
         var11 = (Pointer)this.ys.get(var9);
      } else if (var5.pC()) {
         var11 = (Pointer)this.ys.get(var3);
      }

      if (var11 == null) {
         if ((var7 & 1) != 0 && var5.pC != null) {
            var11 = new Pointer(var9, var6, var7);
         } else {
            var11 = this.UT.pC(this.wS, var9, var6, var5.pC() || var7 == 1);
         }
      }

      if (var11 != null) {
         if (var11 != null) {
            if (var11.getType() == 1) {
               if (var11.getSize() == -2) {
                  this.wS.getModel().getReferenceManager().recordInternalReference(var1, var9, ReferenceType.GEN_DATA);
               } else {
                  this.pC(var8, var1, Long.valueOf(var9));
               }

               return;
            }

            if (var11.getType() != 0) {
               var8.add(new PointerLocation(var1, var11));
               this.ys.put(var9, var11);
               return;
            }
         }

         long var12 = var9;
         long var14 = 0L;
         String var16 = DataStringUtil.getValidStringAt(this.wS.getModel(), this.wS.getMemory(), var9, 1, -1);
         if (var16 != null) {
            var14 = var16.length();
         }

         label135:
         if (var5.pC() || var5.pC != null && var5.pC != 0L) {
            long var17;
            try {
               var17 = this.pC(var9, var14, var6);
               if (var17 >= 0L) {
                  if (var3 < var9) {
                     var16 = DataStringUtil.getValidStringAt(this.wS.getModel(), this.wS.getMemory(), var3, 1, -1);
                     if (var16 != null) {
                        var14 = var16.length();
                     }

                     if (var3 + var14 > var9 && this.pC(var3, var14, var6) < 0L) {
                        var12 = var3;
                        break label135;
                     }

                     return;
                  }

                  return;
               }
            } catch (MemoryException var19) {
               break label135;
            }

            if (var17 == -2L) {
               return;
            }
         }

         if (var5.pC()) {
            boolean var21 = false;
            if ((var7 & 2) == 0 && this.E.pC(this.wS.getProcessor().getDefaultMode() == 64 ? 64 : 0, var3)) {
               var21 = true;
            }

            if (this.wS.getProcessor().getMode() == 64) {
               if (var21) {
                  return;
               }

               if (var14 > 0L) {
                  if (this.wS instanceof a) {
                     ((a)this.wS).pC(var3, -1L, null, 1, -1, true);
                  }

                  if (var14 != 1L) {
                     this.pC(var8, var1, var3, 0, 2, 0);
                     return;
                  }
               }
            } else {
               if (var14 > 0L) {
                  if (this.wS instanceof a) {
                     ((a)this.wS).pC(var3, -1L, null, 1, -1, true);
                  }

                  this.pC(var8, var1, var3, 0, 2, 0);
                  return;
               }

               if (var21) {
                  return;
               }
            }
         } else if (var5.A == null || !(var5.A instanceof ZV)) {
            INativeContinuousItem var22 = this.wS.getModel().getItemOver(var9);
            if (var22 == null
               && (var6 == 1 || var14 > 4L || var12 != var9)
               && DataStringUtil.isValidStringAt(this.wS.getModel(), this.wS.getMemory(), var12, 3, -1)) {
               if (this.wS instanceof a) {
                  ((a)this.wS).pC(var12, -1L, null, 3, -1, true);
               }

               var8.add(new PointerLocation(var1, new Pointer(var12, 0, var7)));
               return;
            }
         }

         this.pC(var1, var3, var5, var6, var7, var8, var14);
      }
   }

   private void pC(long var1, long var3, AH.Av var5, int var6, int var7, List var8, long var9) {
      int var11 = var6;
      int var12 = var6;
      int var13 = 1;
      int var14 = 0;
      if (var6 == -1) {
         var14 = 65536;
         int var15 = this.wS.getTypeManager().getPointerSize();
         Long var16 = VirtualMemoryUtil.readAsLongSafe(this.wS.getMemory(), var3, var15);
         if (var16 == null) {
            return;
         }

         var13 = 16;
         int var17 = yj.pC(var16, var15);
         if (var17 > 0) {
            var12 = var17;
            if (var17 == 4) {
               Long var18 = VirtualMemoryUtil.readAsLongSafe(this.wS.getMemory(), var3, var17);
               if ((var18 & -128L) == 0L && Characters.isAsciiCharOrCommonFormat(var16.intValue())) {
                  var12 = 1;
                  var11 = 4;
                  var14 = 0;
               }
            } else if (var17 == 1) {
               var13 = 64;
            }
         } else {
            var12 = var15;
         }

         if (var11 < 0) {
            var11 = var12;
         }
      }

      if (var5.pC != null) {
         var3 += var5.pC;
         Pointer var22 = this.pC(var8, var1, var3, var12, var7, var14);
         if (var6 != -1) {
            this.ys.put(var3, var22);
         }
      } else {
         if (var5.A instanceof ZV) {
            Z var20 = ((ZV)var5.A).kS();
            if (var20 != null && var20.A() != Z.Av.pC) {
               return;
            }

            if (var20 != null && var20.kS().getOperandType() == 1) {
               var11 = Math.max(var12, 1 << (int)var20.kS().getOperandValue());
               var13 = 1024;
            }
         } else {
            var13 = var12 == 1 ? 64 : 16;
         }

         if (!var5.pC() || var9 != 1L || var12 == 1 && var11 == 4) {
            if (var7 == 0) {
               var7 = 2;
            }

            ArrayList var21 = new ArrayList();
            if (var12 != -1 && var14 != 65536) {
               this.pC(var3, var12, var11, var7, var13, var21);
            } else {
               var21.add(new Pointer(var3, var12, var7));
            }

            this.pC(var21, var1, var8, var14);
         } else {
            this.pC(var8, var1, var3, 0, 2, var14);
         }
      }
   }

   private void pC(List var1, long var2, List var4, int var5) {
      if (!var1.isEmpty()) {
         int var6 = 0;

         for (Pointer var8 : var1) {
            if (var6 != 0 && !pC) {
               this.wS.enqueuePointerForAnalysis(var8, 0, var5);
            } else {
               var4.add(new PointerLocation(var2, var8, var5));
            }

            var6++;
         }
      }
   }

   public void pC(long var1, int var3, int var4, int var5, int var6, List var7) {
      INativeContinuousItem var8 = this.wS.getModel().getNextItem(var1);
      long var9 = var1 + var6 * var3;
      int var11 = var7.size();
      if (var8 != null) {
         var9 = var8.getMemoryAddress();
      }

      try {
         AH.Sv var12 = new AH.Sv();
         byte[] var13 = new byte[0];

         label108:
         for (int var14 = 0; var14 < var6; var14++) {
            boolean var15 = this.pC(var1, var9, var7, var3, var4, var5, var14 != 0, var12);
            if (!var15) {
               break;
            }

            if (var3 < var4) {
               byte[] var16 = new byte[var4 - var3];
               int var17 = this.wS.getMemory().read(var1 + var3, var16.length, var16, 0);
               if (var17 != var16.length) {
                  break;
               }

               if (var14 != 0) {
                  for (int var25 = 0; var25 < var16.length; var25++) {
                     if (var13[var25] == 0 && var16[var25] != 0) {
                        if (var14 > 0 && !var7.isEmpty()) {
                           Pointer var26 = (Pointer)var7.get(var7.size() - 1);
                           if (var26.getAddress() == var1) {
                              var7.remove(var7.size() - 1);
                           }
                        }
                        break label108;
                     }
                  }
               } else {
                  var13 = new byte[var16.length];
                  boolean var18 = false;

                  for (int var19 = 0; var19 < var16.length; var19++) {
                     if (var16[var19] != 0) {
                        var13[var19] = -1;
                     } else {
                        var13[var19] = 0;
                        var18 = true;
                     }
                  }

                  if (!var18) {
                     break;
                  }
               }
            }

            var1 += var4;
         }
      } catch (MemoryException var21) {
      }

      if (var3 == 1 && var4 == 4) {
         try {
            boolean var22 = true;

            for (int var23 = var11; var23 < var7.size(); var23++) {
               if (!Characters.isAsciiCharOrCommonFormat(this.wS.getMemory().readByte(((Pointer)var7.get(var23)).getAddress()))) {
                  var22 = false;
                  break;
               }
            }

            if (var22) {
               for (int var24 = var11; var24 < var7.size(); var24++) {
                  ((a)this.wS).pC(((Pointer)var7.get(var24)).getAddress(), -1L, null, 1, -1, true);
               }
            }
         } catch (MemoryException var20) {
         }
      }
   }

   public boolean pC(long var1, long var3, List var5, int var6, int var7, boolean var8) throws MemoryException {
      return this.pC(var1, var3, var5, var6, var6, var7, var8, null);
   }

   public boolean pC(long var1, long var3, List var5, int var6, int var7, int var8, boolean var9, AH.Sv var10) throws MemoryException {
      int var11 = 0;
      int var12 = this.wS.getMemory().readInt(var1);
      boolean var13 = (var1 & 3L) == 0L;
      if (var13 && var12 == 0) {
         if (!var9) {
            pC(var1, var5, var6, var8);
            return true;
         }

         if (var10 == null || !var10.pC() || var8 == 5 || var6 != 4) {
            return false;
         }

         while (var11 < 3 && var12 == 0) {
            var12 = this.wS.getMemory().readInt(var1 + var6 * ++var11);
         }

         if (var12 == 0) {
            return false;
         }
      }

      if (!var9 || !Vg.pC(var12, this.wS.getProcessor().getMode()) || var6 == 4 && var10 != null && var10.pC(var12, var11 == 0)) {
         int var14 = var12;
         if (var6 == 1) {
            var14 = this.wS.getMemory().readByte(var1) & 255;
         } else if (var6 == 2) {
            var14 = this.wS.getMemory().readShort(var1) & '\uffff';
         }

         if (var10 != null && !var10.pC()) {
            var10.pC(var14);
         }

         if (var6 == 4) {
            if (var8 == 5) {
               if (var9 && !this.wS.getAnalysisRanges().contains(var12)) {
                  return false;
               }
            } else {
               int var15 = var12 & -65536;
               if (var9 && var15 != -65536 && var15 != 0 && (var10 == null || !var10.pC(var12, var11 == 0))) {
                  return false;
               }
            }

            var5.add(new Pointer(var1, var6, var8));
         } else if (var6 == 2) {
            if (var10 == null || !var10.A(var14)) {
               int var17 = var14 & 0xFF00;
               if (var9 && var17 != 65280 && var17 != 0) {
                  return false;
               }
            }

            var5.add(new Pointer(var1, var6, var8));
         } else if (var6 == 1) {
            if (var9) {
               if (var13 && var7 == var6 && (var12 & -256) == 0) {
                  return false;
               }

               if (var13 && var7 == 4 && (var12 & -256) != 0) {
                  return false;
               }

               if (var14 >= 128) {
                  return false;
               }
            }

            String var18 = DataStringUtil.getValidStringAt(this.wS.getModel(), this.wS.getMemory(), var1, 1, -1);
            if (var18 != null) {
               if (var7 == var6) {
                  if (DataStringUtil.isValidStringAt(this.wS.getModel(), this.wS.getMemory(), var1 + 2L, 1, -1)
                     || var13 && DataStringUtil.isValidStringAt(this.wS.getModel(), this.wS.getMemory(), var1 + 4L, 1, -1)) {
                     return false;
                  }
               } else if (var7 > var6 && var18.length() != 1) {
                  return false;
               }
            }

            var5.add(new Pointer(var1, var6, var8));
         } else {
            byte[] var19 = new byte[var6];
            int var16 = this.wS.getMemory().read(var1, var6, var19, 0);
            if (var16 != var6) {
               return false;
            }

            if (var9) {
               return false;
            }

            pC(var1, var5, var6, var8);
         }

         return true;
      } else {
         this.UT.pC(var1);
         return false;
      }
   }

   public static void pC(long var0, List var2, int var3, int var4) {
      if (var3 == 0) {
         var2.add(new Pointer(var0, var3, var4));
      }

      while (var3 > 0) {
         int var5 = var3 > 8 ? 8 : var3;
         var2.add(new Pointer(var0, var5, var4));
         var0 += var5;
         var3 -= var5;
      }
   }

   public long pC(long var1, long var3, int var5) throws MemoryException {
      INativeContinuousItem var6 = this.wS.getModel().getPreviousItem(var1);
      if ((var6 == null || var6.getMemoryAddressEnd() != var1) && var1 != 0L) {
         byte var7 = this.wS.getMemory().readByte(var1 - 1L);
         if (var7 == 0) {
            byte var15 = this.wS.getMemory().readByte(var1);
            if (var15 == 0) {
               long var17 = var1 + 1L;

               for (int var18 = var5 == -1 ? this.wS.getTypeManager().getPointerSize() : var5; var17 < var1 + var18; var17++) {
                  var15 = this.wS.getMemory().readByte(var17);
                  if (var15 != 0) {
                     long var12 = var17;
                     short var14 = 256;

                     while (Characters.isAsciiCharOrCommonFormat(var15) && var12 < Long.MAX_VALUE && var12 < var17 + var14) {
                        var15 = this.wS.getMemory().readByte(++var12);
                     }

                     if (var15 == 0 && var12 > var17 + 2L) {
                        return -2L;
                     }
                     break;
                  }
               }
            }

            return -1L;
         } else {
            byte var8 = this.wS.getMemory().readByte(var1);
            if (var8 != 0 && var3 <= 0L) {
               return -1L;
            } else {
               long var9 = var1 - 1L;

               for (short var11 = 256;
                  Characters.isAsciiCharOrCommonFormat(var7) && var9 > 1L && var9 + var11 > var1;
                  var7 = this.wS.getMemory().readByte(--var9)
               ) {
                  if (var6 != null && var6.getMemoryAddressEnd() == var9) {
                     return this.pC(var1, var9, var6);
                  }
               }

               if (var7 == 0) {
                  return this.pC(var1, var9 + 1L, var6);
               } else {
                  return var5 == -1 && var1 - (var9 + 1L) > 4L ? -2L : -1L;
               }
            }
         }
      } else {
         return -1L;
      }
   }

   private long pC(long var1, long var3, INativeContinuousItem var5) {
      int var6 = this.wS.getProcessor().getInstructionAlignment();

      for (long var7 = var3 & ~(var6 - 1); var7 < var1; var7 += var6) {
         if (this.wS.getProcessor().getMode() == 64) {
            if (PU.wS(PU.pC(this.wS.getProcessor(), this.wS.getMemory(), var7, 64))) {
               return -1L;
            }
         } else {
            if ((var1 & 3L) == 0L && PU.wS(PU.pC(this.wS.getProcessor(), this.wS.getMemory(), var7, 32))) {
               return -1L;
            }

            if (PU.wS(PU.pC(this.wS.getProcessor(), this.wS.getMemory(), var7, 16))) {
               return -1L;
            }
         }
      }

      return var3;
   }

   private void pC(List var1) {
      for (int var2 = 0; var2 < var1.size(); var2++) {
         PointerLocation var3 = (PointerLocation)var1.get(var2);
         if (var3.getPointer().getSize() == this.wS.getTypeManager().getPointerSize() && var3.getPointer().getType() == 2) {
            try {
               long var4 = this.wS.getMemory().readPointer(var3.getPointer().getAddress());

               for (int var6 = var2 + 1; var6 < var1.size(); var6++) {
                  PointerLocation var7 = (PointerLocation)var1.get(var6);
                  if (var7.getPointer().getAddress() == var4) {
                     var1.set(var2, new PointerLocation(var3.getLocation(), new Pointer(var3.getPointer().getAddress(), var3.getPointer().getSize(), 6)));
                     break;
                  }
               }
            } catch (MemoryException var8) {
            }
         }
      }
   }

   private static class Av {
      private Long pC;
      private Yg A;

      private Av() {
      }

      public Av(Long var1) {
         this.pC = var1;
      }

      public Av(Yg var1) {
         this.A = var1;
      }

      public boolean pC() {
         return this.pC == null && this.A == null;
      }
   }

   private static class Sv {
      public Integer pC = null;
      public Integer A = null;

      public boolean pC() {
         return this.pC != null;
      }

      public void pC(int var1) {
         this.pC = var1;
         this.A = var1;
      }

      public boolean A(int var1) {
         return this.pC(var1, true);
      }

      public boolean pC(int var1, boolean var2) {
         if (!this.pC()) {
            return false;
         } else {
            int var3 = this.pC >> 3;
            int var4 = this.A >> 3;
            if (var1 > this.pC - var3 && var1 < this.A + var4) {
               if (var2 && var1 < this.pC) {
                  this.pC = var1;
               } else if (var2 && var1 > this.A) {
                  this.A = var1;
               }

               return true;
            } else if (this.pC != 0 && this.pC == this.A && (this.pC & 65535) == 0 && (var1 & 65535) == 0) {
               return true;
            } else {
               return this.pC == 0 || (this.pC != this.A || !this.kS(this.pC)) && (this.pC == this.A || !this.kS(this.pC) || !this.kS(this.A))
                  ? false
                  : this.kS(var1);
            }
         }
      }

      private boolean kS(int var1) {
         return Character.isAlphabetic(var1 & 0xFF)
            && Character.isAlphabetic(var1 >> 8 & 0xFF)
            && Character.isAlphabetic(var1 >> 16 & 0xFF)
            && Character.isAlphabetic(var1 >> 24 & 0xFF);
      }
   }
}
