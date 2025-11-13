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

public class uU {
   private static final ILogger RF = GlobalLog.getLogger(uU.class);
   public static boolean q = true;
   private static final Set xK = Sets.newHashSet(0L);
   private INativeCodeAnalyzer Dw;
   private hi Uv;
   private SZ oW;
   private Map gO = new HashMap();
   private Map nf = new HashMap();
   private uU.eo gP = new uU.eo();

   public uU(INativeCodeAnalyzer var1, FS var2) {
      this.Dw = var1;
      this.Uv = new hi(var2);
      this.oW = new SZ(var1, var2, true, true);
   }

   public void q() {
      this.gO = null;
      this.gO = new HashMap();
      this.nf = null;
      this.nf = new HashMap();
   }

   public void q(IBasicBlockSkeleton var1, List var2) {
      Mm var3 = (Mm)this.gO.get(var1.getFirstAddress());
      if (var3 == null) {
         var3 = new Mm();
      } else {
         this.gO.remove(var1.getFirstAddress());
      }

      long var4 = var1.getFirstAddress();
      HashMap var6 = new HashMap();

      for (int var7 = 0; var7 < var1.getInsntructions().size(); var7++) {
         fA var8 = (fA)var1.getInsntructions().get(var7);
         CW[] var9 = var8.RF();
         String var10 = var8.Dw().q();
         if (!var3.q() && var8.Dw().xK()) {
            var3.RF();
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

         label264: {
            switch (var12) {
               case 0:
                  var3.q(var8.Dw(), var9[0], var9[1].getOperandValue(var4), var4, Mm.eo.q);
                  break label264;
               case 1:
                  var3.q(var8.Dw(), var9[0], var9[1].getOperandValue(var4), var4, Mm.eo.RF);
                  break label264;
               case 2:
                  if (!var9[1].equals(var9[0])) {
                     if (var3.q(var9[0])) {
                        this.q(var2, var3, var9[0]);
                     }

                     if (var9[1].getOperandType() == 1) {
                        var3.q(var8.Dw(), var9[0], var9[1].getOperandValue(var4), var4, Mm.eo.Dw);
                     } else if (!var3.q() && var9.length == 2 && var9[1].getOperandType() == 0 && var3.q(var9[1])) {
                        var3.q(var8.Dw(), var9[0], var4, var9[1], null, var8.getProcessorMode());
                     }
                  }
                  break label264;
               case 3:
                  CW var13 = (CW)var9[1].merge(var4);
                  var13 = var13 != null ? var13 : var9[1];
                  if (InstructionUtil.isAddressOperand(var13)) {
                     if (var9[0].RF(var8.getProcessorMode())) {
                        this.q(var2, var4, var9[1].getOperandValue(var4), 4, 5);
                     } else {
                        try {
                           long var14 = this.Dw.getMemory().readPointer(var13.getOperandValue(var4));
                           var3.q(var8.Dw(), var9[0], var14, var4, Mm.eo.xK);
                           var3.RF(var9[0]);
                           var6.put(var4, Maps.toMap(Mm.Uv(var9[0]), var13.getOperandValue(var4)));
                           break label264;
                        } catch (MemoryException var26) {
                        }
                     }
                  }
                  break;
               default:
                  if (var3.q()) {
                     break label264;
                  }
            }

            CW var11 = var8.RF() == null ? null : (var8.RF().length > 0 ? var8.RF()[0] : null);
            CW var30 = var8.RF() == null ? null : (var8.RF().length > 1 ? var8.RF()[1] : null);
            CW var32 = var8.RF() == null ? null : (var8.RF().length > 2 ? var8.RF()[2] : null);
            Iterator var17;
            switch (var10) {
               case "ADD":
               case "SUB":
               case "ORR":
                  if (var8.RF().length == 2) {
                     var32 = var30;
                     var30 = var11;
                  }

                  this.q(var3, var30, var32, var6, var8.getProcessorMode());
                  boolean var36 = var3.q(var8.Dw(), var11, var4, var30, var32, var8.getProcessorMode());
                  if (!var36) {
                     this.q(var2, var3, var11);
                  }
                  break label264;
               case "LD1":
               case "LD2":
               case "LD3":
               case "LD4":
               case "ST1":
               case "ST2":
               case "ST3":
               case "ST4":
                  wh var35 = (wh)var30;
                  if (var3.q(var35.xK()) && var11.getOperandType() == 7) {
                     int var38 = 4;
                     Set var39 = var3.RF(var35.xK());
                     int var19 = 0;

                     label237:
                     for (Long var21 : var39) {
                        int var22 = 0;

                        byte var23;
                        while (true) {
                           if (var22 >= var11.oW().length) {
                              continue label237;
                           }

                           var23 = 2;
                           if (!(var11.oW()[var22] instanceof RI)) {
                              break;
                           }

                           int var24 = xB.Dw((RI)var11.oW()[var22]);
                           var38 = xB.xK((RI)var11.oW()[var22]);
                           if (var38 == -1) {
                              var38 = 4;
                              break;
                           }

                           for (int var25 = 0; var25 < var24; var25++) {
                              this.q(var4, var21, new uU.eo((long)var19), var38, var23, var2);
                              var19 += var38;
                           }

                           var22++;
                        }

                        this.q(var4, var21, new uU.eo((long)var19), var38, var23, var2);
                     }

                     if (var10.startsWith("L")) {
                        this.q(var2, var3, var11.oW());
                     }

                     if (var32 != null) {
                        this.q(var2, var3, var30);
                     }
                     break label264;
                  }

                  this.q(var2, var3, var9);
                  break label264;
               case "BLX":
               case "BX":
               case "BR":
               case "BLR":
                  if (var3.q(var11)) {
                     this.q(var2, var4, var3.RF(var11));
                  }

                  if (var8.q().getBranchType() == IInsnEmulator.BranchType.CALL) {
                     CW var34 = GC.gO(this.Dw.getProcessor().getMode());
                     var3.Dw(var34);
                  }
                  break label264;
               case "RET":
                  CW var33 = var11 == null ? GC.gO(this.Dw.getProcessor().getMode()) : var11;
                  if (var3.q(var33)) {
                     this.q(var2, var4, var3.RF(var33));
                  }
                  break label264;
               default:
                  List var16 = this.q(var8, var10);
                  var17 = var16.iterator();
            }

            while (var17.hasNext()) {
               CW var18 = (CW)var17.next();
               this.q(var3, var18, var6);
               var3.RF(var18);
            }

            fV var37 = PG.q(var8);
            if (var37 != null && var37.Hk()) {
               this.q(var3, var2, var4, var8.Dw(), var37, var6, var8.getProcessorMode());
            } else {
               this.q(var2, var3, var9);
            }
         }

         var4 += ((fA)var1.getInsntructions().get(var7)).getSize();
      }

      fA var27 = (fA)var1.getInsntructions().get(var1.getInsntructions().size() - 1);
      long var28 = var4 - var27.getSize();
      IFlowInformation var29 = var27.getBreakingFlow(var28);
      if (var29.isBrokenKnown() && !var27.Uv().oW()) {
         this.gO.put(var28 + var27.getSize(), var3);
      } else {
         this.q(var2, var3);
         this.q(var2);
      }
   }

   private List q(fA var1, String var2) {
      if (Strings.startsWith(var2, "MUL", "SMU", "UMUL", "SMLAL", "SMMUL", "UMAAL", "UMLAL", "MNEG", "SMNEG", "UMNEG")) {
         return Arrays.asList(var1.q(var1.getCountOfOperands() - 2), var1.q(var1.getCountOfOperands() - 1));
      } else {
         return (List)(Strings.startsWith(var2, "MLA", "MLS", "SML", "SMML", "MADD", "MSUB", "SMADD", "SMSUB", "UMADD", "UMSUB")
            ? Arrays.asList(var1.q(var1.getCountOfOperands() - 3), var1.q(var1.getCountOfOperands() - 2))
            : new ArrayList());
      }
   }

   private void q(Mm var1, CW var2, CW var3, Map var4, int var5) {
      boolean var6 = var1.q(var2, var5);
      if (var6 || var1.q(var3, var5)) {
         CW var7 = var6 ? var3 : var2;
         this.q(var1, var7, var4);
      }
   }

   private void q(Mm var1, CW var2, Map var3) {
      long var4 = Mm.Uv(var2);
      Long var6 = var1.xK(var4);
      if (var6 != null) {
         Map var7 = (Map)var3.get(var6);
         if (var7 != null) {
            Long var8 = (Long)var7.get(var4);
            if (var8 != null) {
               this.Uv.q(var8, var6);
            }
         }
      }
   }

   public void q(Mm var1, List var2, long var3, Op var5, fV var6, Map var7, int var8) {
      Set var9 = null;
      Set var10 = null;
      Object var11 = null;
      int var12 = var6.Uv() / 8;
      boolean var13 = false;
      if (InstructionUtil.isAddressOperand(var6.Dw())) {
         var13 = true;
      } else {
         CW var14 = var6.Dw().q(var3);
         if (var14 != null && InstructionUtil.isAddressOperand(var14)) {
            var13 = true;
         }
      }

      wh var26 = null;
      CW var15 = null;
      if (!var13) {
         var26 = var6.Dw();
         var15 = var26.xK();
      }

      boolean var16 = false;
      if (!var13 && var26 != null) {
         if (var1.q(var15)) {
            var16 = true;
         } else if (var15.RF(var8) && var26.gO() != null && var26.gO().getOperandType() == 0 && var1.q(var26.gO())) {
            var16 = true;
         }
      }

      int var17 = var6.lm() == fV.eo.Uv ? 1 : var6.xK();
      if (var16) {
         CW var18 = var26.gO();
         if (var18 == null) {
            var10 = xK;
            var11 = xK;
         } else if (var18.getOperandType() == 1 || var18.getOperandType() == 0 && (var1.q(var18) || var26.gP() && !var26.nf())) {
            var10 = var1.q(var18, var3, var8);
            var11 = var26.nf() ? var10 : xK;
         }

         var9 = var1.q(var15, var3, var8);
         var1.RF(var15);
         var1.RF(var18);
         if (var18 != null) {
            this.q(var1, var15, var18, var7, var8);
         }

         byte var19 = 0;
         if (var6.RF(0).RF(var8)) {
            var19 = 5;
         } else if (var8 == 64) {
            if (var12 != 8) {
               var19 = 2;
            }
         } else if (var12 != 4) {
            var19 = 2;
         }

         for (Long var21 : var9) {
            if (!this.Uv.Dw(var21)) {
               if (var11 == null) {
                  for (int var39 = 0; var39 < var17; var39++) {
                     this.q(var3, var21 + var39 * var12, new uU.eo(var18), var12, var19, var2);
                  }
               } else {
                  if (var17 > 1) {
                     var11 = new TreeSet((Collection)var11);
                     Long var22 = (Long)var11.iterator().next();

                     for (int var23 = 1; var23 < var17; var23++) {
                        Long var24 = var22 + var12 * var23;
                        if (!var11.contains(var24)) {
                           var11.add(var24);
                        }
                     }
                  }

                  for (Long var42 : var11) {
                     this.q(var3, var21, new uU.eo(var42), var12, var19, var2);
                  }
               }
            }
         }
      }

      if (var26 != null && var26.Uv()) {
         if (var9 != null && var10 != null) {
            ArrayList var27 = new ArrayList();
            if (var17 <= 1 && var26.gO() != null) {
               for (Long var33 : var9) {
                  for (Long var40 : var10) {
                     var27.add(var33 + var40);
                  }
               }
            } else {
               int var29 = var17 * var12;

               for (Long var35 : var9) {
                  var27.add(var35 + var29);
               }
            }

            var1.q(var5, var15, var27, var3, Mm.eo.Dw);
         } else {
            this.q(var2, var1, var6.Dw());
         }
      }

      if (var6.nf()) {
         if (var11 != null) {
            for (int var28 = 0; var28 < var6.xK(); var28++) {
               ArrayList var31 = new ArrayList();
               int var34 = var28 * var12;

               for (Long var41 : var9) {
                  for (Long var44 : var11) {
                     Long var25 = VirtualMemoryUtil.readAsLongSafe(this.Dw.getMemory(), var41 + var44 + var34, var12);
                     if (var25 != null && this.Dw.getAnalysisRanges().contains(var25)) {
                        var31.add(var25 + var12 * var28);
                     }
                  }
               }

               if (!var31.isEmpty()) {
                  var1.q(var5, var6.RF(var28), var31, var3, Mm.eo.xK);
                  var1.RF(var6.RF(var28));
                  var7.put(var3, Maps.toMap(Mm.Uv(var6.RF(var28)), (Long)var9.iterator().next()));
               } else {
                  var1.Dw(var6.RF(var28));
               }
            }
         } else {
            this.q(var2, var1, var6.gP());
         }
      }

      if (var6.gO() != null) {
         this.q(var2, var1, var6.gO());
         var1.q(var5, var6.gO(), 0L, var3, Mm.eo.Dw);
      }
   }

   private void q(List var1, Mm var2, CW[] var3) {
      for (Couple var6 : var2.q(var3)) {
         for (Long var8 : (Set)var6.getSecond()) {
            this.q(var1, (Long)var6.getFirst(), var8);
         }
      }
   }

   private void q(List var1, Mm var2) {
      for (Couple var5 : var2.q(this.Dw)) {
         for (Long var7 : (Set)var5.getSecond()) {
            this.q(var1, (Long)var5.getFirst(), var7);
         }
      }
   }

   private void q(List var1, Mm var2, CW var3) {
      List var4 = var2.Dw(var3);
      if (var4 != null) {
         for (Couple var6 : var4) {
            for (Long var8 : (Set)var6.getSecond()) {
               this.q(var1, (Long)var6.getFirst(), var8);
            }
         }
      }
   }

   private void q(List var1, long var2, long var4) {
      this.q(var2, var4, this.gP, -1, 0, var1);
   }

   private void q(List var1, long var2, Set var4) {
      int var5 = this.Dw.getProcessor().getMode() == 64 ? 8 : 4;

      for (Long var7 : var4) {
         this.q(var1, var2, var7, var5, 1);
      }
   }

   private Pointer q(List var1, long var2, long var4, int var6, int var7) {
      if (this.Dw.getAnalysisRanges().contains(var4)) {
         Object var8 = var7 == 1 ? this.Dw.getProcessor().createEntryPoint(var4) : new Pointer(var4, var6, var7);
         var1.add(new PointerLocation(var2, (Pointer)var8));
         return (Pointer)var8;
      } else {
         return null;
      }
   }

   private void q(long var1, long var3, uU.eo var5, int var6, int var7, List var8) {
      long var9 = var3;
      Pointer var11 = null;
      if (var5.q != null) {
         var9 = var3 + var5.q;
         var11 = (Pointer)this.nf.get(var9);
      } else if (var5.q()) {
         var11 = (Pointer)this.nf.get(var3);
      }

      if (var11 == null) {
         if ((var7 & 1) != 0 && var5.q != null) {
            var11 = new Pointer(var9, var6, var7);
         } else {
            var11 = this.Uv.q(this.Dw, var9, var6, var5.q() || var7 == 1);
         }
      }

      if (var11 != null) {
         if (var11 != null) {
            if (var11.getType() == 1) {
               if (var11.getSize() == -2) {
                  this.Dw.getModel().getReferenceManager().recordInternalReference(var1, var9, ReferenceType.GEN_DATA);
               } else {
                  this.q(var8, var1, var9, 0, 1);
               }

               return;
            }

            if (var11.getType() != 0) {
               var8.add(new PointerLocation(var1, var11));
               this.nf.put(var9, var11);
               return;
            }
         }

         long var12 = var9;
         long var14 = 0L;
         String var16 = DataStringUtil.getValidStringAt(this.Dw.getModel(), this.Dw.getMemory(), var9, 1, -1);
         if (var16 != null) {
            var14 = var16.length();
         }

         label127:
         if (var5.q() || var5.q != null && var5.q != 0L) {
            long var17;
            try {
               var17 = this.q(var9, var14, var6);
               if (var17 >= 0L) {
                  if (var3 < var9) {
                     var16 = DataStringUtil.getValidStringAt(this.Dw.getModel(), this.Dw.getMemory(), var3, 1, -1);
                     if (var16 != null) {
                        var14 = var16.length();
                     }

                     if (var3 + var14 > var9 && this.q(var3, var14, var6) < 0L) {
                        var12 = var3;
                        break label127;
                     }

                     return;
                  }

                  return;
               }
            } catch (MemoryException var19) {
               break label127;
            }

            if (var17 == -2L) {
               return;
            }
         }

         if (var5.q()) {
            boolean var21 = false;
            if ((var7 & 2) == 0 && this.oW.q(this.Dw.getProcessor().getDefaultMode() == 64 ? 64 : 0, var3)) {
               var21 = true;
            }

            if (this.Dw.getProcessor().getMode() == 64) {
               if (var21) {
                  return;
               }

               if (var14 > 0L) {
                  if (this.Dw instanceof aae) {
                     ((aae)this.Dw).q(var3, -1L, null, 1, -1, true);
                  }

                  if (var14 != 1L) {
                     this.q(var8, var1, var3, 0, 2);
                     return;
                  }
               }
            } else {
               if (var14 > 0L) {
                  if (this.Dw instanceof aae) {
                     ((aae)this.Dw).q(var3, -1L, null, 1, -1, true);
                  }

                  this.q(var8, var1, var3, 0, 2);
                  return;
               }

               if (var21) {
                  return;
               }
            }
         } else {
            INativeContinuousItem var22 = this.Dw.getModel().getItemOver(var9);
            if (var22 == null && (var6 == 1 || var12 != var9) && DataStringUtil.isValidStringAt(this.Dw.getModel(), this.Dw.getMemory(), var12, 3, -1)) {
               if (this.Dw instanceof aae) {
                  ((aae)this.Dw).q(var12, -1L, null, 3, -1, true);
               }

               var8.add(new PointerLocation(var1, new Pointer(var12, 0, var7)));
               return;
            }
         }

         this.q(var1, var3, var5, var6, var7, var8, var14);
      }
   }

   private void q(long var1, long var3, uU.eo var5, int var6, int var7, List var8, long var9) {
      int var11 = var6;
      int var12 = var6;
      int var13 = 1;
      if (var6 == -1) {
         int var14 = this.Dw.getTypeManager().getPointerSize();
         Long var15 = VirtualMemoryUtil.readAsLongSafe(this.Dw.getMemory(), var3, var14);
         if (var15 == null) {
            return;
         }

         var13 = 16;
         int var16 = aaj.q(var15, var14);
         if (var16 > 0) {
            var12 = var16;
            if (var16 == 4) {
               Long var17 = VirtualMemoryUtil.readAsLongSafe(this.Dw.getMemory(), var3, var16);
               if ((var17 & -128L) == 0L && Characters.isAsciiCharOrCommonFormat(var15.intValue())) {
                  var12 = 1;
                  var11 = 4;
               }
            } else if (var16 == 1) {
               var13 = 64;
            }
         } else {
            var12 = var14;
         }

         if (var11 < 0) {
            var11 = var12;
         }
      }

      if (var5.q != null) {
         var3 += var5.q;
         Pointer var21 = this.q(var8, var1, var3, var12, var7);
         if (var6 != -1) {
            this.nf.put(var3, var21);
         }
      } else {
         if (var5.RF instanceof ZD) {
            DH var19 = ((ZD)var5.RF).xK();
            if (var19 != null && var19.RF() != DH.eo.q) {
               return;
            }

            if (var19 != null && var19.xK().getOperandType() == 1) {
               var11 = Math.max(var12, 1 << (int)var19.xK().getOperandValue());
               var13 = 1024;
            }
         } else {
            var13 = var12 == 1 ? 64 : 16;
         }

         if (!var5.q() || var9 != 1L || var12 == 1 && var11 == 4) {
            if (var7 == 0) {
               var7 = 2;
            }

            ArrayList var20 = new ArrayList();
            this.q(var3, var12, var11, var7, var13, var20);
            this.q(var20, var1, var8);
         } else {
            this.q(var8, var1, var3, 0, 2);
         }
      }
   }

   private void q(List var1, long var2, List var4) {
      if (!var1.isEmpty()) {
         int var5 = 0;

         for (Pointer var7 : var1) {
            if (var5 != 0 && !q) {
               this.Dw.enqueuePointerForAnalysis(var7);
            } else {
               var4.add(new PointerLocation(var2, var7));
            }

            var5++;
         }
      }
   }

   public void q(long var1, int var3, int var4, int var5, int var6, List var7) {
      INativeContinuousItem var8 = this.Dw.getModel().getNextItem(var1);
      long var9 = var1 + var6 * var3;
      int var11 = var7.size();
      if (var8 != null) {
         var9 = var8.getMemoryAddress();
      }

      try {
         uU.CU var12 = new uU.CU();
         byte[] var13 = new byte[0];

         label108:
         for (int var14 = 0; var14 < var6; var14++) {
            boolean var15 = this.q(var1, var9, var7, var3, var4, var5, var14 != 0, var12);
            if (!var15) {
               break;
            }

            if (var3 < var4) {
               byte[] var16 = new byte[var4 - var3];
               int var17 = this.Dw.getMemory().read(var1 + var3, var16.length, var16, 0);
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
               if (!Characters.isAsciiCharOrCommonFormat(this.Dw.getMemory().readByte(((Pointer)var7.get(var23)).getAddress()))) {
                  var22 = false;
                  break;
               }
            }

            if (var22) {
               for (int var24 = var11; var24 < var7.size(); var24++) {
                  ((aae)this.Dw).q(((Pointer)var7.get(var24)).getAddress(), -1L, null, 1, -1, true);
               }
            }
         } catch (MemoryException var20) {
         }
      }
   }

   public boolean q(long var1, long var3, List var5, int var6, int var7, boolean var8) throws MemoryException {
      return this.q(var1, var3, var5, var6, var6, var7, var8, null);
   }

   public boolean q(long var1, long var3, List var5, int var6, int var7, int var8, boolean var9, uU.CU var10) throws MemoryException {
      int var11 = 0;
      int var12 = this.Dw.getMemory().readInt(var1);
      boolean var13 = (var1 & 3L) == 0L;
      if (var13 && var12 == 0) {
         if (!var9) {
            q(var1, var5, var6, var8);
            return true;
         }

         if (var10 == null || !var10.q() || var8 == 5 || var6 != 4) {
            return false;
         }

         while (var11 < 3 && var12 == 0) {
            var12 = this.Dw.getMemory().readInt(var1 + var6 * ++var11);
         }

         if (var12 == 0) {
            return false;
         }
      }

      if (!var9 || !Ij.q(var12, this.Dw.getProcessor().getMode()) || var6 == 4 && var10 != null && var10.q(var12, var11 == 0)) {
         int var14 = var12;
         if (var6 == 1) {
            var14 = this.Dw.getMemory().readByte(var1) & 255;
         } else if (var6 == 2) {
            var14 = this.Dw.getMemory().readShort(var1) & '\uffff';
         }

         if (var10 != null && !var10.q()) {
            var10.q(var14);
         }

         if (var6 == 4) {
            if (var8 == 5) {
               if (var9 && !this.Dw.getAnalysisRanges().contains(var12)) {
                  return false;
               }
            } else {
               int var15 = var12 & -65536;
               if (var9 && var15 != -65536 && var15 != 0 && (var10 == null || !var10.q(var12, var11 == 0))) {
                  return false;
               }
            }

            var5.add(new Pointer(var1, var6, var8));
         } else if (var6 == 2) {
            if (var10 == null || !var10.RF(var14)) {
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

            String var18 = DataStringUtil.getValidStringAt(this.Dw.getModel(), this.Dw.getMemory(), var1, 1, -1);
            if (var18 != null) {
               if (var7 == var6) {
                  if (DataStringUtil.isValidStringAt(this.Dw.getModel(), this.Dw.getMemory(), var1 + 2L, 1, -1)
                     || var13 && DataStringUtil.isValidStringAt(this.Dw.getModel(), this.Dw.getMemory(), var1 + 4L, 1, -1)) {
                     return false;
                  }
               } else if (var7 > var6 && var18.length() != 1) {
                  return false;
               }
            }

            var5.add(new Pointer(var1, var6, var8));
         } else {
            byte[] var19 = new byte[var6];
            int var16 = this.Dw.getMemory().read(var1, var6, var19, 0);
            if (var16 != var6) {
               return false;
            }

            if (var9) {
               return false;
            }

            q(var1, var5, var6, var8);
         }

         return true;
      } else {
         this.Uv.q(var1);
         return false;
      }
   }

   public static void q(long var0, List var2, int var3, int var4) {
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

   public long q(long var1, long var3, int var5) throws MemoryException {
      INativeContinuousItem var6 = this.Dw.getModel().getPreviousItem(var1);
      if ((var6 == null || var6.getMemoryAddressEnd() != var1) && var1 != 0L) {
         byte var7 = this.Dw.getMemory().readByte(var1 - 1L);
         if (var7 == 0) {
            byte var15 = this.Dw.getMemory().readByte(var1);
            if (var15 == 0) {
               long var17 = var1 + 1L;

               for (int var18 = var5 == -1 ? this.Dw.getTypeManager().getPointerSize() : var5; var17 < var1 + var18; var17++) {
                  var15 = this.Dw.getMemory().readByte(var17);
                  if (var15 != 0) {
                     long var12 = var17;
                     short var14 = 256;

                     while (Characters.isAsciiCharOrCommonFormat(var15) && var12 < Long.MAX_VALUE && var12 < var17 + var14) {
                        var15 = this.Dw.getMemory().readByte(++var12);
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
            byte var8 = this.Dw.getMemory().readByte(var1);
            if (var8 != 0 && var3 <= 0L) {
               return -1L;
            } else {
               long var9 = var1 - 1L;

               for (short var11 = 256;
                  Characters.isAsciiCharOrCommonFormat(var7) && var9 > 1L && var9 + var11 > var1;
                  var7 = this.Dw.getMemory().readByte(--var9)
               ) {
                  if (var6 != null && var6.getMemoryAddressEnd() == var9) {
                     return this.q(var1, var9, var6);
                  }
               }

               if (var7 == 0) {
                  return this.q(var1, var9 + 1L, var6);
               } else {
                  return var5 == -1 && var1 - (var9 + 1L) > 4L ? -2L : -1L;
               }
            }
         }
      } else {
         return -1L;
      }
   }

   private long q(long var1, long var3, INativeContinuousItem var5) {
      int var6 = this.Dw.getProcessor().getInstructionAlignment();

      for (long var7 = var3 & ~(var6 - 1); var7 < var1; var7 += var6) {
         if (this.Dw.getProcessor().getMode() == 64) {
            if (OC.Dw(OC.q(this.Dw.getProcessor(), this.Dw.getMemory(), var7, 64))) {
               return -1L;
            }
         } else {
            if ((var1 & 3L) == 0L && OC.Dw(OC.q(this.Dw.getProcessor(), this.Dw.getMemory(), var7, 32))) {
               return -1L;
            }

            if (OC.Dw(OC.q(this.Dw.getProcessor(), this.Dw.getMemory(), var7, 16))) {
               return -1L;
            }
         }
      }

      return var3;
   }

   private void q(List var1) {
      for (int var2 = 0; var2 < var1.size(); var2++) {
         PointerLocation var3 = (PointerLocation)var1.get(var2);
         if (var3.getPointer().getSize() == this.Dw.getTypeManager().getPointerSize() && var3.getPointer().getType() == 2) {
            try {
               long var4 = this.Dw.getMemory().readPointer(var3.getPointer().getAddress());

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

   private static class CU {
      public Integer q = null;
      public Integer RF = null;

      public boolean q() {
         return this.q != null;
      }

      public void q(int var1) {
         this.q = var1;
         this.RF = var1;
      }

      public boolean RF(int var1) {
         return this.q(var1, true);
      }

      public boolean q(int var1, boolean var2) {
         if (!this.q()) {
            return false;
         } else {
            int var3 = this.q >> 3;
            int var4 = this.RF >> 3;
            if (var1 > this.q - var3 && var1 < this.RF + var4) {
               if (var2 && var1 < this.q) {
                  this.q = var1;
               } else if (var2 && var1 > this.RF) {
                  this.RF = var1;
               }

               return true;
            } else if (this.q != 0 && this.q == this.RF && (this.q & 65535) == 0 && (var1 & 65535) == 0) {
               return true;
            } else {
               return this.q == 0 || (this.q != this.RF || !this.xK(this.q)) && (this.q == this.RF || !this.xK(this.q) || !this.xK(this.RF))
                  ? false
                  : this.xK(var1);
            }
         }
      }

      private boolean xK(int var1) {
         return Character.isAlphabetic(var1 & 0xFF)
            && Character.isAlphabetic(var1 >> 8 & 0xFF)
            && Character.isAlphabetic(var1 >> 16 & 0xFF)
            && Character.isAlphabetic(var1 >> 24 & 0xFF);
      }
   }

   private static class eo {
      private Long q;
      private CW RF;

      private eo() {
      }

      public eo(Long var1) {
         this.q = var1;
      }

      public eo(CW var1) {
         this.RF = var1;
      }

      public boolean q() {
         return this.q == null && this.RF == null;
      }
   }
}
