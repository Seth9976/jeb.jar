package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeModel;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReferenceManager;
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
import java.util.Map.Entry;

public class aaj {
   private static final ILogger q = GlobalLog.getLogger(aaj.class);
   private final INativeCodeAnalyzer RF;
   private final ITypeManager xK;
   private List Dw = new ArrayList();
   private Set Uv = new HashSet();

   public aaj(INativeCodeAnalyzer var1) {
      this.RF = var1;
      this.xK = var1.getTypeManager();
   }

   public INativeDataItem q(long var1, String var3, int var4, INativeType var5, boolean var6) {
      return this.q(var1, var3, var4, var5, var6, false);
   }

   private void q(long var1, String var3, int var4) {
      if (this.Dw != null) {
         aaj.eo var5 = new aaj.eo(var1, var3, var4);
         this.Dw.add(var5);
         this.Uv.add(var1);
      }
   }

   public INativeDataItem q(long var1, String var3, int var4, INativeType var5, boolean var6, boolean var7) {
      if (this.Dw != null && var7) {
         this.q(var1, var3, var4);
         return null;
      } else {
         Object var8 = null;
         if (var5 != null && var5.getSize() == var4) {
            var8 = var5;
         }

         if (var8 == null && var4 > 0) {
            var8 = this.xK.getExactInteger(var4, false);
         }

         if (var8 == null) {
            if (var5 != null) {
               var8 = var5;
            } else {
               var8 = this.xK.getExactInteger(1, false);
            }
         }

         if (var4 == 0 && ((INativeType)var8).getSize() == 1) {
            try {
               long var9 = this.RF.getMemory().readPointer(var1);
               if (this.RF.getAnalysisRanges().contains(var9)) {
                  var8 = this.xK.getVoidReference();
               }
            } catch (MemoryException var16) {
            }
         }

         if (var3 == null) {
            INativeContinuousItem var17 = this.RF.getModel().getItemAt(var1);
            if (var17 != null) {
               var3 = var17.getName(true);
            }
         }

         Object var18 = null;
         boolean var10 = false;
         aal var11 = ((aae)this.RF).qa();
         if (var4 > 0 && var4 == ((INativeType)var8).getSize()) {
            INativeContinuousItem var12 = var11.q(var1);
            if (var12 != null && var12.getMemoryAddressEnd() == var1 && !(var12 instanceof INativeStringItem) && !var11.RF(var12)) {
               var18 = this.RF.defineData(var1, (INativeType)var8);
            }

            if (var12 != null && var12 instanceof INativeStringItem && var12.getMemoryAddressEnd() + 16L >= var1) {
               var10 = true;
            }
         }

         if (var18 == null && (var10 || this.RF(var1, var4))) {
            var18 = var11.q(var1, var4, ((INativeType)var8).getSize(), var6);
         }

         int var19 = this.RF.getTypeManager().getPointerSize();
         if (var18 == null
            && var4 > 0
            && (var4 > ((INativeType)var8).getSize() || var4 > var19 && ((INativeType)var8).getSize() != var4)
            && !((aae)this.RF).q().intersects(var1, var1 + var4)) {
            if (this.Dw != null && var6) {
               this.q(var1, var3, var4);
               return null;
            }

            if (var4 % var19 == 0) {
               int var13 = var4 / var19;
               var8 = this.xK.getExactInteger(var19, false);
               var18 = this.RF.defineData(var1, (INativeType)var8);

               for (int var14 = 1; var14 < var13; var14++) {
                  Pointer var15 = new Pointer(var1 + var19 * var14, var19, 2);
                  this.RF.enqueuePointerForAnalysis(var15, 0, var13 > 10 ? 4096 : 0);
               }
            } else {
               q.error(S.L("Invalid data element at %Xh expected size %d"), var1, var4);
            }
         }

         if (var18 == null) {
            var18 = this.RF.defineData(var1, (INativeType)var8);
         }

         if (var3 != null) {
            ((INativeDataItem)var18).setName(var3);
         }

         return (INativeDataItem)var18;
      }
   }

   private boolean RF(long var1, int var3) {
      IReferenceManager var4 = this.RF.getModel().getReferenceManager();
      Long var5 = null;
      if (var3 > 0) {
         try {
            if (this.RF.getMemory().readByte(var1 + var3 - 1L) == 0) {
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

   public void q() {
      int var1 = this.RF.getTypeManager().getPointerSize();
      INativeType var2 = this.xK.getExactInteger(var1, false);
      HashMap var3 = new HashMap();
      INativeType var4 = this.xK.getType(var2.getName() + "_transient");
      if (var4 == null) {
         IAliasType var14 = this.xK.createAlias(var2.getName() + "_transient", var2);
         var14.addFlags(128);
      }

      HashMap var5 = new HashMap();
      aai var6 = new aai(this.RF, this);

      for (aaj.eo var8 : this.Dw) {
         if (!var8.Dw) {
            try {
               Object var9 = this.RF(var8.q, var8.RF, var8.xK);
               if (var9 == null) {
                  aai.oM var10 = new aai.oM(this.RF, this, var8.q, var8.RF, var8.xK);
                  if (var10.q) {
                     var9 = this.q(var8.q, var1, var10, var3, var5);
                  } else if (var8.xK == 16
                     && !var10.RF
                     && var10.q(var8.q)
                     && VirtualMemoryUtil.readAsLongSafe(this.RF.getMemory(), var8.q, 8) == 0L
                     && VirtualMemoryUtil.readAsLongSafe(this.RF.getMemory(), var8.q + 8L, 8) == 0L) {
                     INativeType var11 = this.xK.getExactInteger(var8.xK, false);
                     var9 = this.RF.defineData(var8.q, var11);
                  } else {
                     var9 = var6.q(var8.q, var8.xK, var8.RF, var1, var10);
                     if (var9 == null) {
                        var9 = var6.RF(var8.q, var8.xK, var8.RF, var1, var10);
                     }
                  }

                  if (var9 == null) {
                     var9 = this.q(var8.q, var1, var10, var3, var5);
                  }
               }

               if (var9 != null && var8.RF != null) {
                  ((INativeContinuousItem)var9).setName(var8.RF);
               }
            } catch (JebException var12) {
            } catch (Exception var13) {
               q.catchingSilent(new Exception(Strings.ff("Can not define data at %xh", var8.q), var13));
            }

            var8.Dw = true;
         }
      }

      this.Dw = null;
   }

   private INativeDataItem RF(long var1, String var3, int var4) {
      INativeContinuousItem var5 = this.RF.getModel().getItemOver(var1 + var4);
      if (var5 != null && var5.getMemoryAddress() < var1 + var4) {
         if (var5 instanceof INativeStringItem var6
            && var5 instanceof axi
            && DataStringUtil.createItemNameFromString(((INativeStringItem)var5).getValue(), 16).equals(((axi)var5).RF(true))) {
            int var7 = (int)(var6.getMemoryAddressEnd() - (var1 + var4));
            if (var7 > 1) {
               ((aae)this.RF).q(var1 + var4, var1 + var4 + var6.getMemorySize(), var6.getStringType(), var7 - 1, var7, true);
            } else {
               var5.dispose(true);
            }
         } else if (RF(var5) && this.RF.getModel().getReferenceManager().getReferencesTo(var5.getMemoryAddress()).isEmpty()) {
            var5.dispose(true);
         } else {
            q.catchingSilent(new JebException(Strings.ff("Found collision at %xh", var1)));
         }
      }

      INativeContinuousItem var8 = this.RF.getModel().getItemAt(var1);
      return var8 != null && var8.getMemorySize() >= var4 ? (INativeDataItem)var8 : null;
   }

   private INativeContinuousItem q(long var1, int var3, aai.oM var4, Map var5, Map var6) {
      if (!var4.q && var1 % var3 != 0L) {
         INativeType var13 = this.xK.getExactInteger(1, false);
         return this.RF.defineData(var1, var13);
      } else {
         Object var7 = null;

         for (Entry var9 : var4) {
            INativeContinuousItem var10 = this.RF.getModel().getItemAt((Long)var9.getKey());
            if ((Long)var9.getKey() == var1) {
               var7 = var10;
            }

            if (var10 == null) {
               Object var11;
               if ((Long)var9.getKey() == var1) {
                  var11 = (INativeType)var5.get(var9.getValue());
                  if (var11 == null) {
                     var11 = this.xK.getExactInteger((Integer)var9.getValue(), false);
                     var5.put((Integer)var9.getValue(), var11);
                  }
               } else {
                  var11 = (INativeType)var6.get(var9.getValue());
                  if (var11 == null) {
                     INativeType var12 = this.xK.getExactInteger((Integer)var9.getValue(), false);
                     var11 = this.xK.getType(var12.getName() + "_transient");
                     if (var11 == null) {
                        var11 = this.xK.createAlias(var12.getName() + "_transient", var12);
                        ((INativeType)var11).addFlags(128);
                        var6.put((Integer)var9.getValue(), var11);
                     }
                  }
               }

               if (var11 != null) {
                  INativeDataItem var14 = this.RF.defineData((Long)var9.getKey(), (INativeType)var11);
                  if ((Long)var9.getKey() == var1) {
                     var7 = var14;
                  }
               }
            }
         }

         return (INativeContinuousItem)var7;
      }
   }

   public static int q(long var0, int var2) {
      if (var0 != 0L && var0 != -1L) {
         if (var2 == 8) {
            long var3 = var0 & 4294967295L;
            long var5 = var0 >>> 32 & 4294967295L;
            if (var3 == 0L) {
               return q(var5, 4);
            }

            if (var5 == 0L && (var3 & -65536L) == 0L) {
               return -2;
            }

            if ((var3 & 4294901760L) == 4294901760L && var5 == 4294967295L) {
               return var2;
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

   public boolean q(long var1) {
      return this.Uv.contains(var1);
   }

   public boolean RF(long var1) {
      for (aaj.eo var4 : this.Dw) {
         if (!var4.Dw && var1 >= var4.q && var1 < var4.q + var4.xK) {
            return true;
         }
      }

      return false;
   }

   public static Boolean q(INativeCodeAnalyzer var0, long var1) {
      INativeContinuousItem var3 = var0.getModel().getItemAt(var1);
      if (!(var3 instanceof INativeDataItem)) {
         return null;
      } else {
         INativeType var4 = ((INativeDataItem)var3).getType();
         if (var4 == null) {
            return Boolean.FALSE;
         } else {
            String var5 = var4.getName();
            return q(var0, var5) ? Boolean.FALSE : Boolean.TRUE;
         }
      }
   }

   public static Boolean RF(INativeCodeAnalyzer var0, long var1) {
      INativeContinuousItem var3 = var0.getModel().getItemOver(var1);
      return q(var0, var1, var3);
   }

   public static Boolean q(INativeCodeAnalyzer var0, long var1, INativeContinuousItem var3) {
      if (!(var3 instanceof INativeDataItem)) {
         return null;
      } else {
         INativeType var4 = ((INativeDataItem)var3).getType();
         if (var4 == null) {
            return Boolean.FALSE;
         } else {
            String var5 = var4.getName();
            if (q(var0, var5)) {
               return Boolean.FALSE;
            } else if (var3.getMemoryAddress() == var1) {
               return Boolean.TRUE;
            } else if (!(var3 instanceof INativeStringItem var6)) {
               INativeDataItem var7 = q((INativeDataItem)var3, var1);
               if (var7 == null) {
                  return Boolean.FALSE;
               } else {
                  return !(var7.getType() instanceof IReferenceType) ? Boolean.FALSE : Boolean.TRUE;
               }
            } else if (var1 != var3.getMemoryAddressEnd() - 1L && (var6.getStringType().getBasicCharSize() != 2 || var1 != var3.getMemoryAddressEnd() - 2L)) {
               return var6.getStringType().getBasicCharSize() == 2 && (var1 - var3.getMemoryAddress()) % 2L != 0L
                  ? Boolean.FALSE
                  : DataStringUtil.isValidCharAt(var0.getMemory(), var1, var3.getMemoryAddressEnd(), var6.getStringType());
            } else {
               return Boolean.TRUE;
            }
         }
      }
   }

   public static boolean xK(INativeCodeAnalyzer var0, long var1) {
      INativeContinuousItem var3 = var0.getModel().getItemOver(var1);
      if (!(var3 instanceof INativeDataItem)) {
         return false;
      } else {
         INativeType var4 = ((INativeDataItem)var3).getType();
         if (var4 == null) {
            return false;
         } else {
            String var5 = var4.getName();
            return q(var0, var5);
         }
      }
   }

   public static boolean q(INativeCodeAnalyzer var0, String var1) {
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

   public static Couple q(INativeCodeAnalyzer var0, INativeContinuousItem var1, long var2) {
      return !(var1 instanceof axv) ? null : q(var0, var1.getMemoryAddress(), (int)var1.getMemorySize(), q(var1), ((axv)var1).Uv(), var2);
   }

   public static Couple q(INativeCodeAnalyzer var0, long var1, int var3) {
      return q(var0, var1, var3, true, null, 0L);
   }

   public static Couple q(INativeCodeAnalyzer var0, long var1, int var3, boolean var4, INativeType var5, long var6) {
      IVirtualMemory var8 = var0.getMemory();
      boolean var9 = true;
      if (!(var5 instanceof bbt) && (var6 < 0L || !var4)) {
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
               } else if (!Booleans.isTrue(q(var0, var10, var11))) {
                  return null;
               }
            }

            return !var9 && var11 instanceof INativeInstructionItem var12 && !q(var0.getModel(), var12) ? null : new Couple(var10, var11);
         }
      }
   }

   private static INativeDataItem q(INativeDataItem var0, long var1) {
      List var3 = var0.getChildren();
      if (var3 != null) {
         for (INativeDataItem var5 : var3) {
            if (var1 >= var5.getMemoryAddress() && var1 < var5.getMemoryAddressEnd()) {
               return q(var5, var1);
            }
         }
      }

      return var0.getMemoryAddress() == var1 ? var0 : null;
   }

   public static boolean q(INativeCodeModel var0, INativeInstructionItem var1) {
      if (((aaf)var0).oW(var1.getMemoryAddress()) != null) {
         return true;
      } else {
         if (var0.isBasicBlockHeader(var1.getMemoryAddress()) || var0.getContainedRoutineAddresses(var1.getMemoryAddress()).isEmpty()) {
            INativeContinuousItem var2 = var0.getPreviousItem(var1);
            if (var2.getMemoryAddressEnd() == var1.getMemoryAddress()) {
               if (var2 instanceof INativeInstructionItem var3) {
                  IFlowInformation var4 = var3.getInstruction().getBreakingFlow(var3.getMemoryAddress());
                  if (q(var4, false)) {
                     return true;
                  }

                  var4 = var3.getInstruction().getRoutineCall(var3.getMemoryAddress());
                  if (q(var4, true)) {
                     return true;
                  }

                  if (q(var4, false) && var4.isBrokenKnown()) {
                     long var5 = ((ICodePointer)var4.getTargets().get(0)).getAddress();
                     axp var7 = ((aaf)var0).oW(var5);
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

   private static boolean q(IFlowInformation var0, boolean var1) {
      return var0.isBroken() && var0.getTargets().size() == 1 && (!var1 || var0.noFallThrough());
   }

   public static boolean q(INativeContinuousItem var0) {
      if (var0 instanceof INativeDataItem) {
         DataHints var1 = ((INativeDataItem)var0).getHints(false);
         if (var1 != null && var1.getAddressCalculationHint() == 2) {
            return true;
         }
      }

      return false;
   }

   public static boolean RF(INativeContinuousItem var0) {
      String var1 = var0.getName();
      return var1 == null ? true : q(var1) || Uv(var0) || RF(var1);
   }

   public static boolean xK(INativeContinuousItem var0) {
      return q(var0.getName());
   }

   public static boolean q(String var0) {
      return var0.startsWith("gvar_") || var0.startsWith("ptr_");
   }

   public static boolean Dw(INativeContinuousItem var0) {
      return RF(var0.getName());
   }

   public static boolean RF(String var0) {
      return var0.startsWith("ptr_");
   }

   public static boolean Uv(INativeContinuousItem var0) {
      if (var0 instanceof axw && var0 instanceof axi) {
         String var1 = ((axi)var0).RF(true);
         return !var1.startsWith("a") ? false : DataStringUtil.createItemNameFromString(((INativeStringItem)var0).getValue(), 16).equals(var1);
      } else {
         return false;
      }
   }

   private static class eo {
      private long q;
      private String RF;
      private int xK;
      private boolean Dw = false;

      public eo(long var1, String var3, int var4) {
         this(var1, var3, var4, false);
      }

      public eo(long var1, String var3, int var4, boolean var5) {
         this.q = var1;
         this.RF = var3;
         this.xK = var4;
      }
   }
}
