package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeModel;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEventType;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataHints;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataStringUtil;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeStringItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.IAliasType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class yj {
   private static final ILogger pC = GlobalLog.getLogger(yj.class);
   private final a A;
   private final ITypeManager kS;
   private List wS = new ArrayList();
   private Set UT = new HashSet();

   public yj(a var1) {
      this.A = var1;
      this.kS = var1.ld();
   }

   public INativeDataItem pC(long var1, String var3, int var4, INativeType var5, boolean var6) {
      return this.pC(var1, var3, var4, var5, var6, false);
   }

   private void pC(long var1, String var3, int var4) {
      if (this.wS != null) {
         yj.Av var5 = new yj.Av(var1, var3, var4);
         this.wS.add(var5);
         this.UT.add(var1);
      }
   }

   public INativeDataItem pC(long var1, String var3, int var4, INativeType var5, boolean var6, boolean var7) {
      boolean var8 = var4 == 0 && (var5 == null || var5.getSize() == 1);
      if (this.wS != null && var7) {
         this.pC(var1, var3, var4);
         return null;
      } else {
         Object var9 = null;
         if (var5 != null && var5.getSize() == var4) {
            var9 = var5;
         }

         if (var9 == null && var4 > 0) {
            var9 = this.kS.getExactInteger(var4, false);
         }

         if (var9 == null) {
            if (var5 != null) {
               var9 = var5;
            } else {
               var9 = this.kS.getExactInteger(1, false);
            }
         }

         if (var4 == 0 && ((INativeType)var9).getSize() == 1) {
            try {
               long var10 = this.A.getMemory().readPointer(var1);
               if (this.A.getAnalysisRanges().contains(var10)) {
                  var9 = this.kS.getVoidReference();
               }
            } catch (MemoryException var15) {
            }
         }

         if (var3 == null) {
            INativeContinuousItem var16 = this.A.ys().getItemAt(var1);
            if (var16 != null) {
               var3 = var16.getName(true);
            }
         }

         INativeDataItem var17 = this.pC(var1, var4, (INativeType)var9, var6, var8, false);
         int var11 = this.A.ld().getPointerSize();
         if (var17 == null
            && var4 > 0
            && (var4 > ((INativeType)var9).getSize() || var4 > var11 && ((INativeType)var9).getSize() != var4)
            && !this.A.pC().intersects(var1, var1 + var4)) {
            if (this.wS != null && var6) {
               this.pC(var1, var3, var4);
               return null;
            }

            if (var4 % var11 == 0) {
               int var12 = var4 / var11;
               var9 = this.kS.getExactInteger(var11, false);
               var17 = this.A.pC(var1, (aye)var9, -1, true);

               for (int var13 = 1; var13 < var12; var13++) {
                  Pointer var14 = new Pointer(var1 + var11 * var13, var11, 2);
                  this.A.enqueuePointerForAnalysis(var14, 0, var12 > 10 ? 4096 : 0);
               }
            } else {
               pC.error(S.L("Invalid data element at %Xh expected size %d"), var1, var4);
            }
         }

         if (var17 == null) {
            var17 = this.A.pC(var1, (aye)var9, -1, true);
         }

         if (var8 && var17.getType().getSize() == 1) {
            var17.setAttribute("UnknownSize", Boolean.TRUE);
         }

         if (var3 != null) {
            var17.setName(var3);
         }

         return var17;
      }
   }

   private INativeDataItem pC(long var1, int var3, INativeType var4, boolean var5, boolean var6, boolean var7) {
      Object var8 = null;
      boolean var9 = false;
      boolean var10 = false;
      boolean var11 = false;
      CC var12 = this.A.NS();
      if (var3 > 0 && var4 != null && var3 == var4.getSize() || var6) {
         INativeContinuousItem var13 = var12.pC(var1);
         if (var13 != null && var13.getMemoryAddressEnd() == var1 && !(var13 instanceof INativeStringItem) && !var12.A(var13) && var4 != null) {
            var11 = true;
         }

         INativeContinuousItem var14 = var12.A(var1);
         if (var13 != null && var13 instanceof INativeStringItem && var13.getMemoryAddressEnd() + 16L >= var1) {
            var9 = true;
         } else if (var14 != null && var14 instanceof INativeStringItem && var14.getMemoryAddressEnd() - 16L <= var1) {
            var9 = true;
         }

         if (var4 == null && var13 != null && var14 != null && var14.getMemoryAddress() - var13.getMemoryAddressEnd() <= 4L) {
            var10 = true;
         }
      }

      if (var8 == null && (var9 || this.A(var1, var3))) {
         var8 = var12.pC(var1, var3, var4 == null ? 1 : var4.getSize(), var5, var9 && var6, var7);
      }

      if (var8 == null && var10) {
         var8 = var12.kS(var1, var12.A(var1).getMemoryAddress());
      }

      if (var8 == null && var11) {
         var8 = this.A.pC(var1, (aye)var4, -1, true);
      }

      return (INativeDataItem)var8;
   }

   private boolean A(long var1, int var3) {
      ey var4 = this.A.ys().gp();
      Long var5 = null;
      if (var3 > 0) {
         try {
            if (this.A.getMemory().readByte(var1 + var3 - 1L) == 0) {
               return true;
            }
         } catch (MemoryException var12) {
         }

         Set var6 = var4.getReferencesTo(var1 + var3);
         if (var6.isEmpty()) {
            return true;
         } else if (var6.size() != 1) {
            return false;
         } else {
            IReference var7 = (IReference)var6.iterator().next();
            if (var7.getFrom().isInternalAddress()) {
               var5 = var7.getFrom().getInternalAddress();
            }

            if (var5 == null) {
               return true;
            } else {
               for (IReference var10 : var4.getReferencesTo(var1)) {
                  if (var10.getFrom().isInternalAddress()) {
                     Long var11 = var10.getFrom().getInternalAddress();
                     if (var5 >= var11 - 8L && var5 <= var11 + 8L) {
                        return true;
                     }
                  }
               }

               return false;
            }
         }
      } else {
         return true;
      }
   }

   public void pC(long var1, long var3) {
      this.pC();
      SortedMap var5 = this.A.ys().getItemsInRange(var1, true, var1 + var3, true, var0 -> var0 instanceof INativeDataItem);
      boolean var6 = this.A.ys().pC(false);

      try {
         this.pC(var1, var5);
      } finally {
         this.A.ys().pC(var6);
         this.A.ys().notifyListenersOfModelChange(MemoryModelEventType.GENERAL_UPDATE, null);
      }
   }

   private void pC() {
      int var1 = this.A.ld().getPointerSize();
      INativeType var2 = this.kS.getExactInteger(var1, false);
      HashMap var3 = new HashMap();
      INativeType var4 = this.kS.getType(var2.getName() + "_transient");
      if (var4 == null) {
         IAliasType var14 = this.kS.createAlias(var2.getName() + "_transient", var2);
         var14.addFlags(128);
      }

      HashMap var5 = new HashMap();
      si var6 = new si(this.A, this);

      for (yj.Av var8 : this.wS) {
         if (!var8.wS) {
            try {
               Object var9 = this.A(var8.pC, var8.A, var8.kS);
               if (var9 == null) {
                  si.bO var10 = new si.bO(this.A, this, var8.pC, var8.A, var8.kS);
                  if (var10.pC) {
                     var9 = this.pC(var8.pC, var1, var10, var3, var5);
                  } else if (var8.kS == 16
                     && !var10.A
                     && var10.pC(var8.pC)
                     && VirtualMemoryUtil.readAsLongSafe(this.A.getMemory(), var8.pC, 8) == 0L
                     && VirtualMemoryUtil.readAsLongSafe(this.A.getMemory(), var8.pC + 8L, 8) == 0L) {
                     INativeType var11 = this.kS.getExactInteger(var8.kS, false);
                     var9 = this.A.pC(var8.pC, (aye)var11, -1, true);
                  } else {
                     var9 = var6.pC(var8.pC, var8.kS, var8.A, var1, var10);
                     if (var9 == null) {
                        var9 = var6.A(var8.pC, var8.kS, var8.A, var1, var10);
                     }
                  }

                  if (var9 == null) {
                     var9 = this.pC(var8.pC, var1, var10, var3, var5);
                  }
               }

               if (var9 != null && var8.A != null) {
                  ((INativeContinuousItem)var9).setName(var8.A);
               }
            } catch (JebException var12) {
            } catch (Exception var13) {
               pC.catchingSilent(new Exception(Strings.ff("Can not define data at %xh", var8.pC), var13));
            }

            var8.wS = true;
         }
      }

      this.wS = null;
   }

   private INativeDataItem A(long var1, String var3, int var4) {
      INativeContinuousItem var5 = this.A.ys().getItemOver(var1 + var4);
      if (var5 != null && var5.getMemoryAddress() < var1 + var4) {
         if (var5 instanceof INativeStringItem var6
            && var5 instanceof aun
            && DataStringUtil.createItemNameFromString(((INativeStringItem)var5).getValue(), 16).equals(((aun)var5).A(true))) {
            int var7 = (int)(var6.getMemoryAddressEnd() - (var1 + var4));
            if (var7 > 1) {
               this.A.pC(var1 + var4, var1 + var4 + var6.getMemorySize(), var6.getStringType(), var7 - 1, var7, true);
            } else {
               var5.dispose(true);
            }
         } else if (A(var5) && this.A.ys().gp().getReferencesTo(var5.getMemoryAddress()).isEmpty()) {
            var5.dispose(true);
         } else {
            pC.catchingSilent(new JebException(Strings.ff("Found collision at %xh", var1)));
         }
      }

      INativeContinuousItem var8 = this.A.ys().getItemAt(var1);
      return var8 != null && var8.getMemorySize() >= var4 ? (INativeDataItem)var8 : null;
   }

   private INativeContinuousItem pC(long var1, int var3, si.bO var4, Map var5, Map var6) {
      if (!var4.pC && var1 % var3 != 0L) {
         INativeType var13 = this.kS.getExactInteger(1, false);
         return this.A.pC(var1, (aye)var13, -1, true);
      } else {
         Object var7 = null;

         for (Entry var9 : var4) {
            INativeContinuousItem var10 = this.A.ys().getItemAt((Long)var9.getKey());
            if ((Long)var9.getKey() == var1) {
               var7 = var10;
            }

            if (var10 == null) {
               Object var11;
               if ((Long)var9.getKey() == var1) {
                  var11 = (INativeType)var5.get(var9.getValue());
                  if (var11 == null) {
                     var11 = this.kS.getExactInteger((Integer)var9.getValue(), false);
                     var5.put((Integer)var9.getValue(), var11);
                  }
               } else {
                  var11 = (INativeType)var6.get(var9.getValue());
                  if (var11 == null) {
                     INativeType var12 = this.kS.getExactInteger((Integer)var9.getValue(), false);
                     var11 = this.kS.getType(var12.getName() + "_transient");
                     if (var11 == null) {
                        var11 = this.kS.createAlias(var12.getName() + "_transient", var12);
                        ((INativeType)var11).addFlags(128);
                        var6.put((Integer)var9.getValue(), var11);
                     }
                  }
               }

               if (var11 != null) {
                  INativeDataItem var14 = this.A.defineData((Long)var9.getKey(), (INativeType)var11);
                  if ((Long)var9.getKey() == var1) {
                     var7 = var14;
                  }
               }
            }
         }

         return (INativeContinuousItem)var7;
      }
   }

   public static int pC(long var0, int var2) {
      if (var0 != 0L && var0 != -1L) {
         if (var2 == 8) {
            long var3 = var0 & 4294967295L;
            long var5 = var0 >>> 32 & 4294967295L;
            if (var3 == 0L) {
               return pC(var5, 4);
            }

            if (var5 == 0L && (var3 & -65536L) == 0L) {
               return -2;
            }

            if ((var3 & 4294901760L) == 4294901760L && var5 == 4294967295L) {
               return var2;
            }

            if (var3 != 0L && var5 != 0L && var3 != 4294967295L && var5 != 4294967295L && var3 == var5 && (var3 & 65535L) != (var3 & -65536L) >>> 16) {
               return 4;
            }

            if (((var3 & 4294901760L) == 0L || (var3 & 4294901760L) == 4294901760L) && ((var5 & 4294901760L) == 0L || (var5 & 4294901760L) == 4294901760L)) {
               return 4;
            }

            if (((var3 & 65280L) == 0L || (var3 & 65280L) == 65280L)
               && ((var3 & 4278190080L) == 0L || (var3 & 4278190080L) == 4278190080L)
               && ((var5 & 65280L) == 0L || (var5 & 65280L) == 65280L)
               && ((var5 & 4278190080L) == 0L || (var5 & 4278190080L) == 4278190080L)) {
               return 2;
            }

            if ((var3 & -522133280L) == 0L && (var5 & -522133280L) == 0L) {
               return 1;
            }
         } else if (var2 == 4) {
            if ((var0 & 4294967040L) == 0L) {
               return -2;
            }

            if ((var0 & 4294901760L) == 0L || (var0 & 4294901760L) == 4294901760L) {
               return var2;
            }

            if (((var0 & 65280L) == 0L || (var0 & 65280L) == 65280L) && ((var0 & 4278190080L) == 0L || (var0 & 4278190080L) == 4278190080L)) {
               return 2;
            }

            if ((var0 & -522133280L) == 0L) {
               return 1;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public boolean pC(long var1) {
      return this.UT.contains(var1);
   }

   public boolean A(long var1) {
      for (yj.Av var4 : this.wS) {
         if (!var4.wS && var1 >= var4.pC && var1 < var4.pC + var4.kS) {
            return true;
         }
      }

      return false;
   }

   public static Boolean pC(INativeCodeAnalyzer var0, long var1) {
      INativeContinuousItem var3 = var0.getModel().getItemAt(var1);
      if (!(var3 instanceof INativeDataItem)) {
         return null;
      } else {
         INativeType var4 = ((INativeDataItem)var3).getType();
         if (var4 == null) {
            return Boolean.FALSE;
         } else {
            String var5 = var4.getName();
            return pC(var0, var5) ? Boolean.FALSE : Boolean.TRUE;
         }
      }
   }

   public static Boolean A(INativeCodeAnalyzer var0, long var1) {
      INativeContinuousItem var3 = var0.getModel().getItemOver(var1);
      return pC(var0, var1, var3, true);
   }

   public static Boolean pC(INativeCodeAnalyzer var0, long var1, INativeContinuousItem var3, boolean var4) {
      if (!(var3 instanceof INativeDataItem)) {
         return var3 instanceof INativeInstructionItem
               && var3.getMemoryAddress() != var1
               && var0.getProcessor().createEntryPoint(var1).getAddress() != var3.getMemoryAddress()
            ? Boolean.FALSE
            : null;
      } else {
         INativeType var5 = ((INativeDataItem)var3).getType();
         if (var5 == null) {
            return Boolean.FALSE;
         } else {
            String var6 = var5.getName();
            if (pC(var0, var6)) {
               return Boolean.FALSE;
            } else if (var3.getMemoryAddress() == var1) {
               return Boolean.TRUE;
            } else if (!(var3 instanceof INativeStringItem var7)) {
               if (var4) {
                  INativeDataItem var8 = pC((INativeDataItem)var3, var1);
                  if (var8 == null) {
                     return Boolean.FALSE;
                  }

                  if (!(var8.getType() instanceof IReferenceType)) {
                     return Boolean.FALSE;
                  }
               }

               return Boolean.TRUE;
            } else if (var1 != var3.getMemoryAddressEnd() - 1L && (var7.getStringType().getBasicCharSize() != 2 || var1 != var3.getMemoryAddressEnd() - 2L)) {
               return var7.getStringType().getBasicCharSize() == 2 && (var1 - var3.getMemoryAddress()) % 2L != 0L
                  ? Boolean.FALSE
                  : DataStringUtil.isValidCharAt(var0.getMemory(), var1, var3.getMemoryAddressEnd(), var7.getStringType());
            } else {
               return Boolean.TRUE;
            }
         }
      }
   }

   public static boolean kS(INativeCodeAnalyzer var0, long var1) {
      INativeContinuousItem var3 = var0.getModel().getItemOver(var1);
      if (!(var3 instanceof INativeDataItem)) {
         return false;
      } else {
         INativeType var4 = ((INativeDataItem)var3).getType();
         if (var4 == null) {
            return false;
         } else {
            String var5 = var4.getName();
            return pC(var0, var5);
         }
      }
   }

   public static boolean pC(INativeCodeAnalyzer var0, String var1) {
      if (var0.getContainer() instanceof IELFUnit) {
         if (var1.startsWith("Elf32") || var1.startsWith("Elf64")) {
            if (!var1.equals("Elf32_Dyn") && !var1.equals("Elf64_Dyn")) {
               return true;
            }

            return false;
         }
      } else if (var0.getContainer() instanceof IPECOFFUnit && var1.startsWith("IMAGE_")) {
         return true;
      }

      return false;
   }

   public static Couple pC(INativeCodeAnalyzer var0, INativeContinuousItem var1, long var2) {
      return !(var1 instanceof ava) ? null : pC(var0, var1.getMemoryAddress(), (int)var1.getMemorySize(), pC(var1), ((ava)var1).UT(), var2);
   }

   public static Couple pC(INativeCodeAnalyzer var0, long var1, int var3, boolean var4, INativeType var5, long var6) {
      IVirtualMemory var8 = var0.getMemory();
      boolean var9 = true;
      if (!(var5 instanceof ayt) && (var6 < 0L || !var4)) {
         if (var5 == null || var5.getSize() * 8 != var8.getSpaceBits() || var5 != var0.getTypeManager().getInteger(var5.getSize(), false)) {
            return null;
         }

         var9 = false;
      }

      Long var10 = VirtualMemoryUtil.readAsUnsignedLongSafe(var8, var0.getProcessor().getEndianness(), var1, var3);
      if (var10 == null) {
         return null;
      } else {
         if (var4) {
            var10 = var10 + var6;
         }

         if (!var0.getAnalysisRanges().contains(var10)) {
            return null;
         } else if (!var9 && var10 < 256L) {
            return null;
         } else {
            INativeContinuousItem var11 = var0.getModel().getItemAt(var10);
            if (var11 == null) {
               var11 = var0.getModel().getItemOver(var10);
               if (var11 instanceof INativeInstructionItem) {
                  if (var0.getProcessor().createEntryPoint(var10).getAddress() != var11.getMemoryAddress()) {
                     return null;
                  }

                  if (var0.getModel().getBasicBlockHeader(var11.getMemoryAddress()) == null) {
                     return null;
                  }
               } else if (!Booleans.isTrue(pC(var0, var10, var11, true))) {
                  return null;
               }
            }

            return !var9 && var11 instanceof INativeInstructionItem var12 && !pC(var0.getModel(), var12) ? null : new Couple(var10, var11);
         }
      }
   }

   private static INativeDataItem pC(INativeDataItem var0, long var1) {
      List var3 = var0.getChildren();
      if (var3 != null) {
         for (INativeDataItem var5 : var3) {
            if (var1 >= var5.getMemoryAddress() && var1 < var5.getMemoryAddressEnd()) {
               return pC(var5, var1);
            }
         }
      }

      return var0.getMemoryAddress() == var1 ? var0 : null;
   }

   public static boolean pC(INativeCodeModel var0, INativeInstructionItem var1) {
      if (((Tw)var0).E(var1.getMemoryAddress()) != null) {
         return true;
      } else {
         if (var0.isBasicBlockHeader(var1.getMemoryAddress()) || var0.getContainedRoutineAddresses(var1.getMemoryAddress()).isEmpty()) {
            INativeContinuousItem var2 = var0.getPreviousItem(var1);
            if (var2.getMemoryAddressEnd() == var1.getMemoryAddress()) {
               if (var2 instanceof INativeInstructionItem var3) {
                  IFlowInformation var4 = var3.getInstruction().getBreakingFlow(var3.getMemoryAddress());
                  if (pC(var4, false)) {
                     return true;
                  }

                  var4 = var3.getInstruction().getRoutineCall(var3.getMemoryAddress());
                  if (pC(var4, true)) {
                     return true;
                  }

                  if (pC(var4, false) && var4.isBrokenKnown()) {
                     long var5 = ((ICodePointer)var4.getTargets().get(0)).getAddress();
                     auu var7 = ((Tw)var0).E(var5);
                     if (var7 != null && var7.getNonReturning() == Boolean.TRUE) {
                        return true;
                     }
                  }
               } else if (var2 instanceof INativeDataItem) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private static boolean pC(IFlowInformation var0, boolean var1) {
      return var0.isBroken() && var0.getTargets().size() == 1 && (!var1 || var0.noFallThrough());
   }

   public static boolean pC(INativeContinuousItem var0) {
      if (var0 instanceof INativeDataItem) {
         DataHints var1 = ((INativeDataItem)var0).getHints(false);
         if (var1 != null && var1.getAddressCalculationHint() == 2) {
            return true;
         }
      }

      return false;
   }

   public static boolean A(INativeContinuousItem var0) {
      String var1 = var0.getName();
      return var1 == null ? true : pC(var1) || wS(var0) || A(var1);
   }

   public static boolean kS(INativeContinuousItem var0) {
      return pC(var0.getName());
   }

   public static boolean pC(String var0) {
      return var0.startsWith("gvar_") || var0.startsWith("ptr_");
   }

   public static boolean A(String var0) {
      return var0.startsWith("ptr_");
   }

   public static boolean wS(INativeContinuousItem var0) {
      if (var0 instanceof avb && var0 instanceof aun) {
         String var1 = ((aun)var0).A(true);
         return !var1.startsWith("a") ? false : DataStringUtil.createItemNameFromString(((INativeStringItem)var0).getValue(), 16).equals(var1);
      } else {
         return false;
      }
   }

   private void pC(long var1, SortedMap var3) {
      if (!var3.isEmpty()) {
         TreeMap var4 = new TreeMap();
         TreeMap var5 = null;
         Object var6 = null;
         Set var7 = null;
         long var8 = (Long)var3.lastKey();

         for (Entry var11 : var3.entrySet()) {
            Object var12 = (INativeContinuousItem)var11.getValue();
            boolean var13 = this.pC(var1, (INativeDataItem)var12, true);
            if (!var13) {
               boolean var14 = false;
               if (((INativeContinuousItem)var12).hasAttribute("UnknownSize")) {
                  INativeDataItem var15 = this.pC((Long)var11.getKey(), 0, null, false, true, true);
                  if (var12 != null && var12 instanceof INativeStringItem) {
                     ((INativeContinuousItem)var12).dispose(false);
                     var12 = var15;
                     var14 = true;
                     Object[] var10000 = new Object[]{var11.getKey()};
                  }
               }

               if (var14 || ((INativeContinuousItem)var12).getMemorySize() > 2L || !A((INativeContinuousItem)var12)) {
                  var6 = null;
               } else if (var6 == null) {
                  var5 = new TreeMap();
                  var5.put((Long)var11.getKey(), var12);
                  var6 = var12;
               } else if (((INativeContinuousItem)var6).getMemorySize() == ((INativeContinuousItem)var12).getMemorySize()
                  && (Long)var5.lastKey() + ((INativeContinuousItem)var6).getMemorySize() == (Long)var11.getKey()) {
                  if (var7 == null) {
                     var7 = this.A.ys().gp().getReferencesTo(((INativeContinuousItem)var6).getMemoryAddress());
                  }

                  if (this.pC(var7, (INativeContinuousItem)var12)) {
                     var5.put((Long)var11.getKey(), var12);
                  } else {
                     var6 = null;
                  }
               } else {
                  var6 = null;
               }

               if ((var6 == null || var8 == (Long)var11.getKey()) && var5 != null) {
                  if (var5.size() > 1) {
                     var4.put((Long)var5.firstKey(), var5);
                  }

                  var5 = null;
                  var7 = null;
                  if (((INativeContinuousItem)var12).getMemorySize() <= 2L && var8 != (Long)var11.getKey()) {
                     var5 = new TreeMap();
                     var5.put((Long)var11.getKey(), var12);
                     var6 = var12;
                  }
               }
            }
         }

         for (Entry var23 : var4.entrySet()) {
            int var24 = ((TreeMap)var23.getValue()).size();
            INativeType var25 = ((INativeDataItem)((TreeMap)var23.getValue()).firstEntry().getValue()).getType();
            int var26 = var25.getSize();
            long var27 = var26 * var24;
            INativeContinuousItem var17 = this.A.ys().getNextItem((Long)((TreeMap)var23.getValue()).lastKey());
            long var18 = var17.getMemoryAddress() - (Long)var23.getKey();
            if (var18 > var27) {
               long var20 = var18 / var26;
               var24 = si.pC(this.A.getMemory(), (Long)var23.getKey(), var26, var24, var20);
            }

            IArrayType var28 = this.kS.createArray(var25, var24);
            this.A.defineData((Long)var23.getKey(), var28);
         }
      }
   }

   private boolean pC(Set var1, INativeContinuousItem var2) {
      Set var3 = this.A.ys().gp().getReferencesTo(var2.getMemoryAddress());
      if (var3.isEmpty()) {
         return false;
      } else {
         for (xy var5 : var3) {
            boolean var6 = false;

            for (xy var8 : var1) {
               if (var8.getFrom().equals(var5.getFrom())) {
                  var6 = true;
                  break;
               }
            }

            if (!var6) {
               return false;
            }
         }

         return true;
      }
   }

   protected boolean pC(long var1, INativeDataItem var3, boolean var4) {
      return this.pC(var1, var3, var3, var4);
   }

   protected boolean pC(long var1, INativeDataItem var3, INativeContinuousItem var4, boolean var5) {
      if (var3 instanceof auw) {
         boolean var14 = false;

         for (INativeDataItem var21 : ((auw)var3).UO()) {
            var14 |= this.pC(var1, var21, var4, false);
         }

         return var14;
      } else if (var3 instanceof auv) {
         aye var13 = ((auv)var3).z();
         if (!(var13 instanceof ayt)
            && var13 != null
            && var13.getSize() * 8 == this.A.getMemory().getSpaceBits()
            && var13 == this.A.ld().pC(var13.getSize(), false)) {
            boolean var15 = true;

            for (INativeDataItem var23 : ((auv)var3).UO()) {
               var15 &= this.pC(var1, var23, var4, false);
               if (!var15) {
                  break;
               }
            }

            if (var15) {
               IReferenceType var19 = this.kS.getVoidReference();
               IArrayType var24 = this.kS.createArray(var19, (int)(var3.getMemorySize() / var19.getSize()));
               this.A.defineData(var3.getMemoryAddress(), var24);
            }
         }

         boolean var16 = false;
         if (((auv)var3).z() instanceof ayt) {
            for (INativeDataItem var25 : ((auv)var3).UO()) {
               var16 |= this.pC(var1, var25, var4, false);
            }
         }

         return var16;
      } else if (!(var3 instanceof ava)) {
         return false;
      } else {
         aye var6 = ((ava)var3).UT();
         Couple var7 = pC(this.A, var3, var1);
         if (var7 == null) {
            return false;
         } else {
            boolean var8 = pC(var3);
            if (!(var6 instanceof ayt) && !var8) {
               DataHints var9 = var3.getHints(false);
               if (var9 != null && var9.getAddressCalculationHint() == 3) {
                  return false;
               }

               if (var5) {
                  INativeDataItem var10 = this.A.defineData(var3.getMemoryAddress(), this.kS.getVoidReference());
                  if (!(var10 instanceof ava)) {
                     this.A.defineData(var3.getMemoryAddress(), var6);
                     return false;
                  }

                  if (var3.getAttributes() != null) {
                     for (Entry var12 : var3.getAttributes().entrySet()) {
                        var10.setAttribute((String)var12.getKey(), var12.getValue());
                     }
                  }

                  if (var3.getName() != null) {
                     var10.setName(var3.getName());
                  }

                  var10.setFlags(var3.getGenericFlags());
                  var3 = var10;
               }
            }

            this.A
               .ys()
               .gp()
               .recordInternalReference(var3.getMemoryAddress(), ((INativeContinuousItem)var7.getSecond()).getMemoryAddress(), ReferenceType.PTR_DATA);
            if (!var8 && var5) {
               if (((INativeContinuousItem)var7.getSecond()).getName(true) == null) {
                  return false;
               } else {
                  String var22 = var3.getName(true);
                  if (this.A.ys().oT().pC(var22)) {
                     String var26 = "ptr_" + this.pC((INativeContinuousItem)var7.getSecond(), (Long)var7.getFirst());
                     var3.setName(var26);
                     HashSet var27 = new HashSet();
                     var27.add(var3.getMemoryAddress());
                     this.pC(var3, var26, var27);
                  }

                  return true;
               }
            } else {
               return true;
            }
         }
      }
   }

   private String pC(INativeContinuousItem var1, Long var2) {
      if (var1.getMemoryAddress() != var2 && wS(var1)) {
         String var4 = DataStringUtil.getStringAt(this.A.getMemory(), var2, 0, (int)var1.getMemorySize(), null);
         return var4 != null && !var4.isEmpty() ? DataStringUtil.createItemNameFromString(var4, 16) : "aNULL";
      } else {
         String var3 = var1.getName(true);
         if (var1.getMemoryAddress() != var2) {
            var3 = var3 + "_" + (var2 - var1.getMemoryAddress());
         }

         return var3;
      }
   }

   private void pC(INativeContinuousItem var1, String var2, Set var3) {
      Set var4 = this.A.ys().gp().getReferencesTo(var1.getMemoryAddress());
      var2 = "ptr_" + var2;

      for (IReference var6 : var4) {
         if (var6.getFrom().isInternalAddress() && !var3.contains(var6.getFrom().getInternalAddress())) {
            INativeContinuousItem var7 = this.A.ys().getItemAt(var6.getFrom().getInternalAddress());
            if (var7 instanceof ava) {
               aye var8 = ((ava)var1).UT();
               if (var8 instanceof ayt) {
                  String var9 = var7.getName(true);
                  if (this.A.ys().oT().pC(var9) && !var2.equals(var7.getName())) {
                     var7.setName(var2);
                     var3.add(var7.getMemoryAddress());
                     this.pC(var7, var2, var3);
                  }
               }
            }
         }
      }
   }

   private static class Av {
      private long pC;
      private String A;
      private int kS;
      private boolean wS = false;

      public Av(long var1, String var3, int var4) {
         this(var1, var3, var4, false);
      }

      public Av(long var1, String var3, int var4, boolean var5) {
         this.pC = var1;
         this.A = var3;
         this.kS = var4;
      }
   }
}
