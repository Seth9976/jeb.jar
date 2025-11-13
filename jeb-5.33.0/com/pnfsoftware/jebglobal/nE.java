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
public class nE {
   private static final ILogger pC = GlobalLog.getLogger(nE.class);
   @SerId(1)
   private C A;
   @SerTransient
   private ave kS;
   @SerTransient
   private avm wS;
   @SerTransient
   private avo UT;
   @SerTransient
   private avi E;
   @SerTransient
   private avk sY;
   @SerTransient
   private avg ys;

   nE(C var1) {
      this.A = var1;
   }

   public ave pC() {
      if (this.kS == null) {
         this.kS = new ave(this.A.getVirtualImageBase());
      }

      return this.kS;
   }

   public avm A() {
      if (this.wS == null) {
         this.wS = new avm();
      }

      return this.wS;
   }

   public avo kS() {
      if (this.UT == null) {
         this.UT = new avo();
      }

      return this.UT;
   }

   public avi wS() {
      if (this.E == null) {
         this.E = new avi(this.A.getVirtualImageBase());
      }

      return this.E;
   }

   public avk UT() {
      if (this.sY == null) {
         this.sY = new avk(this.A);
      }

      return this.sY;
   }

   public avg E() {
      if (this.ys == null) {
         this.ys = new avg(this.A.A());
      }

      return this.ys;
   }

   public String pC(long var1) {
      INativeItem var3 = this.A(var1);
      if (var3 == null) {
         return null;
      } else {
         Long var4 = this.pC(var3);
         return var4 == null ? null : Long.toHexString(var4).toUpperCase() + "h";
      }
   }

   public Long pC(INativeItem var1) {
      if (var1 instanceof aun var2) {
         return this.A.getNativeItemAt(var2.getMemoryAddress()) instanceof auv ? null : var2.getMemoryAddress();
      } else {
         if (var1 instanceof avh) {
            Long var3 = this.pC((avh)var1);
            if (var3 != null && this.A.kS(var3)) {
               return var3;
            }
         } else if (var1 instanceof auu) {
            Long var4 = ((auu)var1).getMemoryAddress();
            if (var4 != null) {
               return var4;
            }
         }

         return null;
      }
   }

   public long pC(String var1) {
      if (var1 == null) {
         return 0L;
      } else if (var1.length() == 0) {
         return 0L;
      } else {
         Long var2 = this.pC(var1, AddressConversionPrecision.DEFAULT);
         if (var2 != null) {
            INativeContinuousItem var3 = this.A.getNativeItemAt(var2);
            if (var3 != null && var3.getItemId() != 0L) {
               return var3.getItemId();
            }

            auu var4 = this.A.A(var2);
            if (var4 != null) {
               return var4.getItemId();
            }
         }

         INativeItem var5 = this.A.A().ld(var1);
         if (var5 != null) {
            return var5.getItemId();
         } else {
            return var2 != null ? this.kS(var2) : 0L;
         }
      }
   }

   public ICodeCoordinates A(String var1) {
      if (var1 == null) {
         return null;
      } else if (var1.length() == 0) {
         return null;
      } else {
         Couple var2 = this.wS(var1);
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
               var6 = this.A.A(var9);
            } catch (NumberFormatException var11) {
            }
         } else {
            var6 = (INativeItem)this.A.A.get(var1);
            if (var6 == null && var5 != null) {
               var6 = (INativeItem)this.A.A.get(var5);
            }

            if (var6 != null && (((INativeItem)var6).isDisposed() || !var1.equals(((INativeItem)var6).getName(true)))) {
               this.A.A.remove(var1);
               this.A.A.remove(var5);
               var7 = true;
               var6 = null;
            }
         }

         Long var13 = null;
         if (var6 == null && var4) {
            var13 = this.pC(var1, true);
         }

         if (var13 == null) {
            if (var6 == null) {
               var6 = this.A.A().ld(var1);
            }

            if (var6 == null && var5 != null) {
               var6 = this.A.A().ld(var5);
            }

            if (var6 != null) {
               if (var3 == null) {
                  if (var6 instanceof auu) {
                     if (var7) {
                        this.A.A.put(var1, (auu)var6);
                     }

                     return new MethodCoordinates(((INativeItem)var6).getIndex());
                  }

                  if (var6 instanceof aur) {
                     return new FieldCoordinates(((INativeItem)var6).getIndex());
                  }

                  if (var6 instanceof auq) {
                     return new ClassCoordinates(((INativeItem)var6).getIndex());
                  }
               } else if (var6 instanceof auu && ((auu)var6).isInternal()) {
                  long var15 = ((auu)var6).getMemoryAddress() + var3;
                  if (this.A.getNativeItemAt(var15) instanceof INativeInstructionItem) {
                     return new InstructionCoordinates(((INativeItem)var6).getIndex(), var3.intValue());
                  }

                  return new NativeCoordinates(var15);
               }
            }

            var13 = this.A.getCodeModel().getLabelManager().resolveLabel(var1);
            if (var13 == null && var5 != null) {
               var13 = this.A.getCodeModel().getLabelManager().resolveLabel(var5);
            }
         }

         Long var14 = null;
         if (var13 != null) {
            var14 = var13 + (var3 == null ? 0L : var3);
            auu var10 = this.A.pC(var14, false);
            if (var10 != null && this.A.getNativeItemAt(var14) instanceof INativeInstructionItem) {
               return new InstructionCoordinates(var10.getIndex(), (int)(var14 - var10.getMemoryAddress()));
            }
         }

         return var14 != null ? new NativeCoordinates(var14) : null;
      }
   }

   public String pC(ICodeCoordinates var1, AddressConversionPrecision var2) {
      if (var1 instanceof InstructionCoordinates) {
         int var9 = ((InstructionCoordinates)var1).getMethodId();
         int var12 = ((InstructionCoordinates)var1).getOffset();
         auu var13 = this.A.UT(var9);
         if (var13 == null) {
            return null;
         } else {
            return var2 == AddressConversionPrecision.COARSE
               ? var13.getAddress(false) + "+" + Formatter.toHexString(var12, true) + "h"
               : var13.getAddress() + "+" + Formatter.toHexString(var12, true) + "h";
         }
      } else if (var1 instanceof MethodCoordinates) {
         int var8 = ((MethodCoordinates)var1).getMethodId();
         auu var11 = this.A.UT(var8);
         if (var11 == null) {
            return null;
         } else {
            return var2 == AddressConversionPrecision.COARSE ? var11.getAddress(false) : var11.getAddress();
         }
      } else if (var1 instanceof FieldCoordinates) {
         int var7 = ((FieldCoordinates)var1).getFieldId();
         aur var10 = this.A.pC(var7);
         if (var10 == null) {
            return null;
         } else {
            return var2 == AddressConversionPrecision.COARSE ? var10.getAddress(false) : var10.getAddress();
         }
      } else if (var1 instanceof ClassCoordinates) {
         int var6 = ((ClassCoordinates)var1).getClassId();
         auq var4 = this.A.A(var6);
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
            String var5 = this.A.pC(var3, AutoLabelPolicy.ITEM);
            return var5 == null ? Formatter.toHexString(var3, true) + "h" : var5;
         }
      } else {
         return null;
      }
   }

   public Long pC(String var1, AddressConversionPrecision var2) {
      if (var1 == null) {
         return null;
      } else {
         Long var3 = this.pC(var1, true);
         if (var3 != null && var2 == AddressConversionPrecision.COARSE) {
            return var3;
         } else {
            ICodeCoordinates var4 = this.A(var1);
            if (var4 == null) {
               return var3;
            } else if (var4 instanceof MethodCoordinates) {
               auu var7 = this.A.UT(((MethodCoordinates)var4).getMethodId());
               if (var7 == null) {
                  pC.catchingSilent(new JebRuntimeException("method coord: null routine for address " + var1));
                  return var3;
               } else {
                  return var7.getMemoryAddress();
               }
            } else if (var4 instanceof InstructionCoordinates) {
               auu var5 = this.A.UT(((InstructionCoordinates)var4).getMethodId());
               if (var5 == null) {
                  pC.catchingSilent(new JebRuntimeException("insn coord: null routine for address " + var1));
                  return var3;
               } else {
                  Long var6 = var5.getMemoryAddress();
                  if (var6 == null) {
                     pC.catchingSilent(new JebRuntimeException("null routine address for address " + var1));
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

   public String pC(long var1, int var3) {
      String var4 = null;
      if (var3 == 1) {
         auu var5 = this.A.A(var1);
         if (var5 != null) {
            var4 = var5.getAddress();
         } else {
            var4 = this.A.pC(var1, AutoLabelPolicy.ITEM);
         }
      } else if (var3 == 2) {
         auu var10 = this.A.pC(var1, false);
         if (var10 != null) {
            var4 = var10.getAddress();
            long var6 = var10.E().getMemoryAddress();
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

   private Couple wS(String var1) {
      String var2 = var1.trim();
      Long var3 = null;
      int var4 = var2.indexOf(43);
      if (var4 >= 0) {
         Long var5 = this.pC(var2.substring(var4 + 1), false);
         if (var5 != null) {
            var3 = var5;
            var2 = var2.substring(0, var4);
         }
      } else {
         var4 = var2.indexOf(45);
         if (var4 >= 0) {
            Long var7 = this.pC(var2.substring(var4 + 1), false);
            if (var7 != null) {
               var3 = -var7;
               var2 = var2.substring(0, var4);
            }
         }
      }

      return var3 == null ? new Couple(var1, null) : new Couple(var2, var3);
   }

   private Long pC(String var1, boolean var2) {
      Long var3 = null;
      var1 = var1.trim();
      String var4 = var1.toLowerCase();
      byte var5 = 10;
      if (!var4.startsWith("0x") && !var4.startsWith("-0x") && !var1.endsWith("h")) {
         int var6 = Conversion.getHexPossibility(var1);
         if (var6 == 2) {
            var5 = 16;
         } else if (var6 == 1) {
            IPropertyManager var7 = this.A.getPropertyManager();
            if (var7 != null && var7.getBoolean("PreferHexAddresses")) {
               var3 = Conversion.stringToLong(var1, -1L, 16);
               if (!this.pC(var3, var2)) {
                  var3 = null;
               }
            }
         }
      }

      if (var3 == null) {
         long var9 = Conversion.stringToLong(var1, -1L, var5);
         if (this.pC(var9, var2)) {
            var3 = var9;
         }
      }

      return var3;
   }

   private boolean pC(long var1, boolean var3) {
      return !var3
         ? var1 != -1L
         : var1 != -1L
            && Longs.compareUnsigned(var1, this.A.getVirtualImageBase()) >= 0
            && (this.A.oT() <= 0L || this.A.oT() == this.A.getVirtualImageBase() || Longs.compareUnsigned(var1, this.A.oT()) < 0);
   }

   public IInputLocation kS(String var1) {
      ICodeObjectUnit var2 = this.A.getCodeObjectContainer();
      if (var2 == null) {
         return null;
      } else {
         Long var3 = this.pC(var1, AddressConversionPrecision.DEFAULT);
         if (var3 == null) {
            return null;
         } else {
            byte var4 = 0;
            long var5 = var3 - this.A.getVirtualImageBase();
            long var7 = var2.convertRelativeAddressToFileOffset(var5);
            return var7 == -1L ? null : new FileInputRegionInformation(var7, var4);
         }
      }
   }

   public String pC(IInputLocation var1) {
      ICodeObjectUnit var2 = this.A.getCodeObjectContainer();
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
            long var7 = this.A.getVirtualImageBase() + var5;
            return this.A.getSymbolicStringAddress(var7);
         }
      }
   }

   public INativeItem A(long var1) {
      long var3 = var1 & -72057594037927936L;
      if (var3 == -9151314442816847872L) {
         return this.pC().A(var1);
      } else if (var3 == -9007199254740992000L) {
         return this.A().pC(var1);
      } else if (var3 == -9079256848778919936L) {
         return this.kS().A(var1);
      } else if (var3 == -8935141660703064064L) {
         return this.wS().pC(var1);
      } else if (var3 == -8863084066665136128L) {
         return this.UT().pC(var1);
      } else if (var3 == -8646911284551352320L) {
         return this.E().pC(var1);
      } else if (var3 == -8791026472627208192L) {
         return this.A.A().pC(var1);
      } else if (var3 == -8574853690513424384L) {
         return this.A.kS().pC(var1);
      } else if (var3 == -8718968878589280256L) {
         return this.A.A().A(var1);
      } else if (var3 == -8430738502437568512L) {
         return this.A.A().UT(var1);
      } else if (var3 == -8358680908399640576L) {
         return this.A.A().wS(var1);
      } else {
         return var3 == -8286623314361712640L ? this.A.A().kS(var1) : null;
      }
   }

   public long kS(long var1) {
      avd var3 = this.pC().pC(var1);
      return var3 == null ? 0L : var3.getItemId();
   }

   public long pC(IInstruction var1) {
      avl var2 = this.A().pC(var1.getMnemonic());
      return var2 == null ? 0L : var2.getItemId();
   }

   public long wS(long var1) {
      avn var3 = this.kS().pC(var1);
      return var3 == null ? 0L : var3.getItemId();
   }

   public long A(long var1, int var3) {
      avh var4 = this.wS().pC(var1, var3);
      return var4 == null ? 0L : var4.getItemId();
   }

   public long pC(int var1, int var2) {
      avj var3 = this.UT().pC(var1, var2);
      return var3 == null ? 0L : var3.getItemId();
   }

   public long pC(IStructureType var1, int var2) {
      avf var3 = this.E().pC(var1, var2);
      return var3 == null ? 0L : var3.getItemId();
   }

   public String UT(long var1) {
      INativeItem var3 = this.A(var1);
      if (var3 instanceof avn) {
         long var4 = ((avn)var3).kS();
         return this.A.getProcessor().getRegisterName(var4);
      } else {
         return null;
      }
   }

   private Long pC(avh var1) {
      if (!(this.A.getNativeItemAt(var1.kS()) instanceof aus var3)) {
         return null;
      } else if (var3.getInstruction() == null) {
         return null;
      } else {
         int var4 = var1.wS();
         if (!(InstructionUtil.getOperandByGlobalIndex(var3.getInstruction(), var4) instanceof IInstructionOperandGeneric var6)) {
            return null;
         } else {
            return var6.getOperandType() != 1 ? null : var6.getOperandValue(var1.kS());
         }
      }
   }
}
