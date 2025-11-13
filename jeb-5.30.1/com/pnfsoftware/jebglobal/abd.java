package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.input.FileInputRegionInformation;
import com.pnfsoftware.jeb.core.input.IInputLocation;
import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.code.coordinates.ClassCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.FieldCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.NativeCoordinates;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.processor.InstructionUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;

@Ser
public class abd {
   private static final ILogger q = GlobalLog.getLogger(abd.class);
   @SerId(1)
   private abg RF;
   @SerTransient
   private aya xK;
   @SerTransient
   private ayi Dw;
   @SerTransient
   private ayk Uv;
   @SerTransient
   private aye oW;
   @SerTransient
   private ayg gO;
   @SerTransient
   private ayc nf;

   abd(abg var1) {
      this.RF = var1;
   }

   public aya q() {
      if (this.xK == null) {
         this.xK = new aya(this.RF.getVirtualImageBase());
      }

      return this.xK;
   }

   public ayi RF() {
      if (this.Dw == null) {
         this.Dw = new ayi();
      }

      return this.Dw;
   }

   public ayk xK() {
      if (this.Uv == null) {
         this.Uv = new ayk();
      }

      return this.Uv;
   }

   public aye Dw() {
      if (this.oW == null) {
         this.oW = new aye(this.RF.getVirtualImageBase());
      }

      return this.oW;
   }

   public ayg Uv() {
      if (this.gO == null) {
         this.gO = new ayg(this.RF);
      }

      return this.gO;
   }

   public ayc oW() {
      if (this.nf == null) {
         this.nf = new ayc(this.RF.RF());
      }

      return this.nf;
   }

   public String q(long var1) {
      INativeItem var3 = this.RF(var1);
      if (var3 == null) {
         return null;
      } else {
         Long var4 = this.q(var3);
         return var4 == null ? null : Long.toHexString(var4).toUpperCase() + "h";
      }
   }

   public Long q(INativeItem var1) {
      if (var1 instanceof axi var2) {
         return this.RF.getNativeItemAt(var2.getMemoryAddress()) instanceof axq ? null : var2.getMemoryAddress();
      } else {
         if (var1 instanceof ayd) {
            Long var3 = this.q((ayd)var1);
            if (var3 != null && this.RF.Uv(var3)) {
               return var3;
            }
         } else if (var1 instanceof axp) {
            Long var4 = ((axp)var1).getMemoryAddress();
            if (var4 != null) {
               return var4;
            }
         }

         return null;
      }
   }

   public long q(String var1) {
      if (var1 == null) {
         return 0L;
      } else if (var1.length() == 0) {
         return 0L;
      } else {
         Long var2 = this.q(var1, AddressConversionPrecision.DEFAULT);
         if (var2 != null) {
            INativeContinuousItem var3 = this.RF.getNativeItemAt(var2);
            if (var3 != null && var3.getItemId() != 0L) {
               return var3.getItemId();
            }

            axp var4 = this.RF.RF(var2);
            if (var4 != null) {
               return var4.getItemId();
            }
         }

         INativeItem var5 = this.RF.RF().za(var1);
         if (var5 != null) {
            return var5.getItemId();
         } else {
            return var2 != null ? this.xK(var2) : 0L;
         }
      }
   }

   public ICodeCoordinates RF(String var1) {
      if (var1 == null) {
         return null;
      } else if (var1.length() == 0) {
         return null;
      } else {
         Couple var2 = this.Dw(var1);
         var1 = (String)var2.getFirst();
         Long var3 = (Long)var2.getSecond();
         String var5 = null;
         boolean var4;
         if (var1.endsWith("()")) {
            var5 = var1;
            var1 = var1.substring(0, var1.length() - 2);
            var4 = false;
         } else {
            var4 = true;
         }

         Object var6 = null;
         boolean var7 = false;
         if (var1.startsWith("sub_")) {
            String var8 = var1.substring("sub_".length());

            try {
               long var9 = Long.parseLong(var8, 16);
               var6 = this.RF.RF(var9);
            } catch (NumberFormatException var11) {
            }
         } else {
            var6 = (INativeItem)this.RF.xK.get(var1);
            if (var6 == null && var5 != null) {
               var6 = (INativeItem)this.RF.xK.get(var5);
            }

            if (var6 != null && (((INativeItem)var6).isDisposed() || !var1.equals(((INativeItem)var6).getName(true)))) {
               this.RF.xK.remove(var1);
               this.RF.xK.remove(var5);
               var7 = true;
               var6 = null;
            }
         }

         Long var13 = null;
         if (var6 == null && var4) {
            var13 = this.q(var1, true);
         }

         if (var13 == null) {
            if (var6 == null) {
               var6 = this.RF.RF().za(var1);
            }

            if (var6 == null && var5 != null) {
               var6 = this.RF.RF().za(var5);
            }

            if (var6 != null) {
               if (var3 == null) {
                  if (var6 instanceof axp) {
                     if (var7) {
                        this.RF.xK.put(var1, (axp)var6);
                     }

                     return new MethodCoordinates(((INativeItem)var6).getIndex());
                  }

                  if (var6 instanceof axm) {
                     return new FieldCoordinates(((INativeItem)var6).getIndex());
                  }

                  if (var6 instanceof axl) {
                     return new ClassCoordinates(((INativeItem)var6).getIndex());
                  }
               } else if (var6 instanceof axp && ((axp)var6).isInternal()) {
                  long var15 = ((axp)var6).getMemoryAddress() + var3;
                  if (this.RF.getNativeItemAt(var15) instanceof INativeInstructionItem) {
                     return new InstructionCoordinates(((INativeItem)var6).getIndex(), var3.intValue());
                  }

                  return new NativeCoordinates(var15);
               }
            }

            var13 = this.RF.getCodeModel().getLabelManager().resolveLabel(var1);
            if (var13 == null && var5 != null) {
               var13 = this.RF.getCodeModel().getLabelManager().resolveLabel(var5);
            }
         }

         Long var14 = null;
         if (var13 != null) {
            var14 = var13 + (var3 == null ? 0L : var3);
            axp var10 = this.RF.q(var14, false);
            if (var10 != null && this.RF.getNativeItemAt(var14) instanceof INativeInstructionItem) {
               return new InstructionCoordinates(var10.getIndex(), (int)(var14 - var10.getMemoryAddress()));
            }
         }

         return var14 != null ? new NativeCoordinates(var14) : null;
      }
   }

   public String q(ICodeCoordinates var1, AddressConversionPrecision var2) {
      if (var1 instanceof InstructionCoordinates) {
         int var9 = ((InstructionCoordinates)var1).getMethodId();
         int var12 = ((InstructionCoordinates)var1).getOffset();
         axp var13 = this.RF.Uv(var9);
         if (var13 == null) {
            return null;
         } else {
            return var2 == AddressConversionPrecision.COARSE
               ? var13.getAddress(false) + "+" + Formatter.toHexString(var12, true) + "h"
               : var13.getAddress() + "+" + Formatter.toHexString(var12, true) + "h";
         }
      } else if (var1 instanceof MethodCoordinates) {
         int var8 = ((MethodCoordinates)var1).getMethodId();
         axp var11 = this.RF.Uv(var8);
         if (var11 == null) {
            return null;
         } else {
            return var2 == AddressConversionPrecision.COARSE ? var11.getAddress(false) : var11.getAddress();
         }
      } else if (var1 instanceof FieldCoordinates) {
         int var7 = ((FieldCoordinates)var1).getFieldId();
         axm var10 = this.RF.q(var7);
         if (var10 == null) {
            return null;
         } else {
            return var2 == AddressConversionPrecision.COARSE ? var10.getAddress(false) : var10.getAddress();
         }
      } else if (var1 instanceof ClassCoordinates) {
         int var6 = ((ClassCoordinates)var1).getClassId();
         axl var4 = this.RF.RF(var6);
         if (var4 == null) {
            return null;
         } else {
            return var2 == AddressConversionPrecision.COARSE ? var4.getAddress(false) : var4.getAddress();
         }
      } else if (var1 instanceof NativeCoordinates) {
         long var3 = ((NativeCoordinates)var1).getAddress();
         if (var2 == AddressConversionPrecision.COARSE) {
            return Formatter.toHexString(var3, true) + "h";
         } else {
            String var5 = this.RF.q(var3, AutoLabelPolicy.ITEM);
            return var5 == null ? Formatter.toHexString(var3, true) + "h" : var5;
         }
      } else {
         return null;
      }
   }

   public Long q(String var1, AddressConversionPrecision var2) {
      if (var1 == null) {
         return null;
      } else {
         Long var3 = this.q(var1, true);
         if (var3 != null && var2 == AddressConversionPrecision.COARSE) {
            return var3;
         } else {
            ICodeCoordinates var4 = this.RF(var1);
            if (var4 == null) {
               return var3;
            } else if (var4 instanceof MethodCoordinates) {
               axp var7 = this.RF.Uv(((MethodCoordinates)var4).getMethodId());
               if (var7 == null) {
                  q.catchingSilent(new JebRuntimeException("method coord: null routine for address " + var1));
                  return var3;
               } else {
                  return var7.getMemoryAddress();
               }
            } else if (var4 instanceof InstructionCoordinates) {
               axp var5 = this.RF.Uv(((InstructionCoordinates)var4).getMethodId());
               if (var5 == null) {
                  q.catchingSilent(new JebRuntimeException("insn coord: null routine for address " + var1));
                  return var3;
               } else {
                  Long var6 = var5.getMemoryAddress();
                  if (var6 == null) {
                     q.catchingSilent(new JebRuntimeException("null routine address for address " + var1));
                     return var3;
                  } else {
                     return var6 + ((InstructionCoordinates)var4).getOffset();
                  }
               }
            } else {
               return var4 instanceof NativeCoordinates ? ((NativeCoordinates)var4).getAddress() : var3;
            }
         }
      }
   }

   public String q(long var1, int var3) {
      String var4 = null;
      if (var3 == 1) {
         axp var5 = this.RF.RF(var1);
         if (var5 != null) {
            var4 = var5.getAddress();
         } else {
            var4 = this.RF.q(var1, AutoLabelPolicy.ITEM);
         }
      } else if (var3 == 2) {
         axp var10 = this.RF.q(var1, false);
         if (var10 != null) {
            var4 = var10.getAddress();
            long var6 = var10.oW().getMemoryAddress();
            long var8 = var1 - var6;
            if (var8 >= 0L) {
               var4 = var4 + "+" + Formatter.toHexString(var8, true) + "h";
            } else if (var8 < 0L) {
               var4 = var4 + "-" + Formatter.toHexString(-var8, true) + "h";
            }
         }
      }

      if (var4 == null) {
         var4 = Formatter.toHexString(var1, true) + "h";
      }

      return var4;
   }

   private Couple Dw(String var1) {
      String var2 = var1.trim();
      Long var3 = null;
      int var4 = var2.indexOf(43);
      if (var4 >= 0) {
         Long var5 = this.q(var2.substring(var4 + 1), false);
         if (var5 != null) {
            var3 = var5;
            var2 = var2.substring(0, var4);
         }
      } else {
         var4 = var2.indexOf(45);
         if (var4 >= 0) {
            Long var7 = this.q(var2.substring(var4 + 1), false);
            if (var7 != null) {
               var3 = -var7;
               var2 = var2.substring(0, var4);
            }
         }
      }

      return var3 == null ? new Couple(var1, null) : new Couple(var2, var3);
   }

   private Long q(String var1, boolean var2) {
      Long var3 = null;
      var1 = var1.trim();
      String var4 = var1.toLowerCase();
      byte var5 = 10;
      if (!var4.startsWith("0x") && !var4.startsWith("-0x") && !var1.endsWith("h")) {
         int var6 = Conversion.getHexPossibility(var1);
         if (var6 == 2) {
            var5 = 16;
         } else if (var6 == 1) {
            IPropertyManager var7 = this.RF.getPropertyManager();
            if (var7 != null && var7.getBoolean("PreferHexAddresses")) {
               var3 = Conversion.stringToLong(var1, -1L, 16);
               if (!this.q(var3, var2)) {
                  var3 = null;
               }
            }
         }
      }

      if (var3 == null) {
         long var9 = Conversion.stringToLong(var1, -1L, var5);
         if (this.q(var9, var2)) {
            var3 = var9;
         }
      }

      return var3;
   }

   private boolean q(long var1, boolean var3) {
      return !var3
         ? var1 != -1L
         : var1 != -1L
            && Longs.compareUnsigned(var1, this.RF.getVirtualImageBase()) >= 0
            && (this.RF.zz() <= 0L || this.RF.zz() == this.RF.getVirtualImageBase() || Longs.compareUnsigned(var1, this.RF.zz()) < 0);
   }

   public IInputLocation xK(String var1) {
      ICodeObjectUnit var2 = this.RF.getCodeObjectContainer();
      if (var2 == null) {
         return null;
      } else {
         Long var3 = this.q(var1, AddressConversionPrecision.DEFAULT);
         if (var3 == null) {
            return null;
         } else {
            byte var4 = 0;
            long var5 = var3 - this.RF.getVirtualImageBase();
            long var7 = var2.convertRelativeAddressToFileOffset(var5);
            return var7 == -1L ? null : new FileInputRegionInformation(var7, var4);
         }
      }
   }

   public String q(IInputLocation var1) {
      ICodeObjectUnit var2 = this.RF.getCodeObjectContainer();
      if (var2 == null) {
         return null;
      } else if (!(var1 instanceof FileInputRegionInformation)) {
         return null;
      } else {
         long var3 = ((FileInputRegionInformation)var1).getOffset();
         long var5 = var2.convertFileOffsetToRelativeAddress(var3);
         if (var5 == -1L) {
            return null;
         } else {
            long var7 = this.RF.getVirtualImageBase() + var5;
            return this.RF.getSymbolicStringAddress(var7);
         }
      }
   }

   public INativeItem RF(long var1) {
      long var3 = var1 & -72057594037927936L;
      if (var3 == -9151314442816847872L) {
         return this.q().RF(var1);
      } else if (var3 == -9007199254740992000L) {
         return this.RF().q(var1);
      } else if (var3 == -9079256848778919936L) {
         return this.xK().RF(var1);
      } else if (var3 == -8935141660703064064L) {
         return this.Dw().q(var1);
      } else if (var3 == -8863084066665136128L) {
         return this.Uv().q(var1);
      } else if (var3 == -8646911284551352320L) {
         return this.oW().q(var1);
      } else if (var3 == -8791026472627208192L) {
         return this.RF.RF().q(var1);
      } else if (var3 == -8574853690513424384L) {
         return this.RF.xK().q(var1);
      } else if (var3 == -8718968878589280256L) {
         return this.RF.RF().RF(var1);
      } else if (var3 == -8430738502437568512L) {
         return this.RF.RF().Uv(var1);
      } else if (var3 == -8358680908399640576L) {
         return this.RF.RF().Dw(var1);
      } else {
         return var3 == -8286623314361712640L ? this.RF.RF().xK(var1) : null;
      }
   }

   public long xK(long var1) {
      axz var3 = this.q().q(var1);
      return var3 == null ? 0L : var3.getItemId();
   }

   public long q(IInstruction var1) {
      ayh var2 = this.RF().q(var1.getMnemonic());
      return var2 == null ? 0L : var2.getItemId();
   }

   public long Dw(long var1) {
      ayj var3 = this.xK().q(var1);
      return var3 == null ? 0L : var3.getItemId();
   }

   public long RF(long var1, int var3) {
      ayd var4 = this.Dw().q(var1, var3);
      return var4 == null ? 0L : var4.getItemId();
   }

   public long q(int var1, int var2) {
      ayf var3 = this.Uv().q(var1, var2);
      return var3 == null ? 0L : var3.getItemId();
   }

   public long q(IStructureType var1, int var2) {
      ayb var3 = this.oW().q(var1, var2);
      return var3 == null ? 0L : var3.getItemId();
   }

   public String Uv(long var1) {
      INativeItem var3 = this.RF(var1);
      if (var3 instanceof ayj) {
         long var4 = ((ayj)var3).xK();
         return this.RF.getProcessor().getRegisterName(var4);
      } else {
         return null;
      }
   }

   private Long q(ayd var1) {
      if (!(this.RF.getNativeItemAt(var1.xK()) instanceof axn var3)) {
         return null;
      } else if (var3.getInstruction() == null) {
         return null;
      } else {
         int var4 = var1.Dw();
         if (!(InstructionUtil.getOperandByGlobalIndex(var3.getInstruction(), var4) instanceof IInstructionOperandGeneric var6)) {
            return null;
         } else {
            return var6.getOperandType() != 1 ? null : var6.getOperandValue(var1.xK());
         }
      }
   }
}
