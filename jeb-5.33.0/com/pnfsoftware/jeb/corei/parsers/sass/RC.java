package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Doubles;
import com.pnfsoftware.jeb.util.primitives.Floats;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.lang.invoke.StringConcatFactory;
import java.util.Collection;
import java.util.List;

@Ser
public class RC extends GenericCodeFormatter {
   public RC() {
      this.setDoNotAttemptImmediateToAddressResolution(true);
      this.getDefaultNumberFormatter().setHexaNotationType(NumberFormatter.HexaNotationType._0x_prefix);
      this.getDefaultAddressFormatter().setHexaNotationType(NumberFormatter.HexaNotationType._0x_prefix);
      this.setMnemonicRightPaddingLength(20);
   }

   @Override
   public String generateHeader() {
      vi var1 = (vi)this.getCodeUnit().getProcessor();
      String var2 = super.generateHeader() + "\n\n";
      String var3 = var1.A();
      return var2 + Strings.ff(S.L("SASS disassembly for compute capability: %s (%s)"), var3, uX.A(var3));
   }

   @Override
   public String getRegisterName(long var1) {
      vi var3 = (vi)this.getCodeUnit().getProcessor();
      boolean var5 = this.getCachedBooleanProperty("DisplayRegisterNumbers");
      String var4;
      if (var5) {
         var4 = var3.pC(var1, false);
      } else {
         var4 = var3.pC(var1, true);
         if (var4.equals("UR63") && var3.kS() < 100) {
            var4 = "URZ";
         }
      }

      return var4;
   }

   public void pC(long var1, sy var3, CodeDocumentPart var4) {
      int var5 = var4.getCurrentLineLength();
      super.formatInstruction(var1, var3, var4);
      if (this.getCachedBooleanProperty("DisplaySchedulingInfo")) {
         int var6 = 0;

         for (Couple var8 : var3.gp) {
            oP var9 = (oP)var8.getFirst();
            oP var10 = (oP)var8.getSecond();
            if (!this.pC(var10)) {
               if (var6 == 0) {
                  int var11 = var4.getCurrentLineLength() - var5;
                  int var12 = Math.max(1, 50 - var11);
                  var4.space(var12);
               } else {
                  var4.space();
               }

               if (var9 != null) {
                  var4.append("&", ItemClassIdentifiers.COMMENT_AUTO);
                  this.pC(var1, var3, var9, 0, 0, var4);
                  var4.append("=");
                  this.pC(var1, var3, var10, 0, 0, var4);
               } else {
                  var4.append("?", ItemClassIdentifiers.COMMENT_AUTO);
                  this.pC(var1, var3, var10, 0, 0, var4);
               }

               var6++;
            }
         }
      }
   }

   protected String pC(long var1, sy var3) {
      boolean var4 = this.getCachedBooleanProperty("DisplayHiddenAttributes");
      return var3.pC(var4);
   }

   protected int pC(sy var1) {
      int var2 = 0;

      for (IInstructionOperand var6 : var1.getOperands()) {
         oP var7 = (oP)var6;
         if (!this.pC(var7)) {
            var2++;
         }
      }

      return var2;
   }

   protected void A(long var1, sy var3, CodeDocumentPart var4) {
      int var5 = 0;

      for (IInstructionOperand var9 : var3.getOperands()) {
         oP var10 = (oP)var9;
         if (!this.pC(var10)) {
            if (var5 >= 1) {
               var4.append(this.getOperandSeparator());
               var4.space();
            }

            this.pC(var1, var3, var10, var5, 0, var4);
            var5++;
         }
      }
   }

   private boolean pC(Collection var1) {
      for (oP var3 : var1) {
         if (!this.pC(var3)) {
            return true;
         }
      }

      return false;
   }

   public void pC(long var1, sy var3, IInstructionOperand var4, int var5, int var6, CodeDocumentPart var7) {
      oP var8 = (oP)var4;
      boolean var9 = false;
      if (var8.wS != 0) {
         if ((var8.wS & 1) != 0) {
            var7.append("!");
         }

         if ((var8.wS & 2) != 0) {
            var7.append("-");
         }

         if ((var8.wS & 4) != 0) {
            var7.append("|");
            var9 = true;
         }

         if ((var8.wS & 8) != 0) {
            var7.append("~");
         }
      }

      boolean var10 = false;
      boolean var11 = !Boolean.FALSE.equals(var3.ld) || this.getCachedBooleanProperty("DisplayImplicitDescriptors");
      int var12 = var8.getOperandType();
      if (var12 == 4098) {
         if (var8.getOperandValue() != 0L) {
            if (var8.pC() == 1) {
               var7.append("c", ItemClassIdentifiers.DEFAULT);
               var7.bracket();
               this.pC(var1, var3, var8.A(), 0, var6 + 1, var7);
               var7.bracketClose();
            } else if (var8.pC() == 2) {
               if (var11) {
                  var7.append("desc", ItemClassIdentifiers.DEFAULT);
                  var7.bracket();
                  this.pC(var1, var3, var8.A(), 0, var6 + 1, var7);
                  var7.bracketClose();
               }
            } else {
               if (var8.pC() != 3) {
                  throw new RuntimeException();
               }

               var7.append("gdesc", ItemClassIdentifiers.DEFAULT);
               var7.bracket();
               this.pC(var1, var3, var8.A(), 0, var6 + 1, var7);
               var7.bracketClose();
            }
         }

         var10 = true;
      } else if (var12 == 4099) {
         if (var8.getOperandValue() != 0L) {
            if (var8.pC() == 1) {
               var7.append("c", ItemClassIdentifiers.DEFAULT);
               var7.bracket();
               this.pC(var1, var3, var8.A(), 0, var6 + 1, var7);
               var7.bracketClose();
            } else {
               if (var8.pC() != 2) {
                  if (var8.pC() == 3) {
                     throw new RuntimeException();
                  }

                  throw new RuntimeException();
               }

               if (var11) {
                  var7.append("desc", ItemClassIdentifiers.DEFAULT);
                  var7.bracket();
                  this.pC(var1, var3, var8.A(), 0, var6 + 1, var7);
                  var7.bracketClose();
               }
            }
         }

         List var13 = var8.kS();
         var7.bracket();
         if (!this.pC(var13)) {
            this.pC(var1, var3, (IInstructionOperand)var13.get(0), 0, var6 + 1, var7);
         } else {
            int var14 = 0;

            for (oP var16 : var13) {
               if (!this.pC(var16)) {
                  if (var14 > 0) {
                     var7.append("+");
                  }

                  this.pC(var1, var3, var16, 0, var6 + 1, var7);
                  var14++;
               }
            }
         }

         var7.bracketClose();
         var10 = true;
      } else if (var12 == 1) {
         String var19;
         if (var8.getOperandBitsize() <= 32) {
            var19 = Formatter.toHexString((int)var8.getOperandValue(), true);
         } else {
            var19 = Formatter.toHexString(var8.getOperandValue(), true);
         }

         var7.append("0x" + var19, ItemClassIdentifiers.IMMEDIATE);
      } else if (var12 == 9) {
         String var20;
         if (var8.getOperandBitsize() <= 32) {
            int var27 = (int)var8.getOperandValue();
            if (var27 >= 0) {
               var20 = "0x" + Integer.toString(var27, 16).toUpperCase();
            } else if (var27 != Integer.MIN_VALUE) {
               var20 = "-0x" + Integer.toString(-var27, 16).toUpperCase();
            } else {
               var20 = "-0x80000000";
            }
         } else {
            long var28 = var8.getOperandValue();
            if (var28 >= 0L) {
               var20 = "0x" + Long.toString(var28, 16).toUpperCase();
            } else if (var28 != Long.MIN_VALUE) {
               var20 = "-0x" + Long.toString(-var28, 16).toUpperCase();
            } else {
               var20 = "-0x8000000000000000";
            }
         }

         var7.append(var20, ItemClassIdentifiers.IMMEDIATE);
      } else if (var12 == 4100) {
         String var21 = "";
         if (var8.getOperandBitsize() == 32) {
            int var29 = (int)var8.getOperandValue();
            int var35 = Floats.getSpecialType(var29);
            if (var35 == 0) {
               float var39 = Float.intBitsToFloat(var29);
               var21 = var21 + Strings.ff("%f", var39);
            } else if (var35 == 1) {
               if (var29 < 0) {
                  var21 = var21 + "-";
               }

               var21 = var21 + "QNAN";
            } else if (var35 == 2) {
               if (var29 < 0) {
                  var21 = var21 + "-";
               }

               var21 = var21 + "SNAN";
            } else if (var35 == 3) {
               var21 = var21 + (var29 < 0 ? "-" : "+");
               var21 = var21 + "INF";
            }
         } else if (var8.getOperandBitsize() == 64) {
            long var30 = var8.getOperandValue();
            int var40 = Doubles.getSpecialType(var30);
            if (var40 == 0) {
               double var17 = Double.longBitsToDouble(var30);
               var21 = var21 + Strings.ff("%f", var17);
            } else if (var40 == 1) {
               if (var30 < 0L) {
                  var21 = var21 + "-";
               }

               var21 = var21 + "QNAN";
            } else if (var40 == 2) {
               if (var30 < 0L) {
                  var21 = var21 + "-";
               }

               var21 = var21 + "SNAN";
            } else if (var40 == 3) {
               var21 = var21 + (var30 < 0L ? "-" : "+");
               var21 = var21 + "INF";
            }
         } else if (var8.getOperandBitsize() == 16) {
            int var31 = (int)var8.getOperandValue();
            float var36 = Floats.fromFP16Bits(var31);
            var21 = var21 + Strings.ff("%f", var36);
         } else {
            var21 = var21 + "?";
         }

         var7.append(var21, ItemClassIdentifiers.IMMEDIATE);
      } else if (var12 == 4097) {
         long var24 = var1 + 16L + var8.getOperandValue();
         this.formatAddress(var24, var7);
      } else if (var12 == 4096) {
         var7.append(StringConcatFactory.makeConcatWithConstants<"makeConcatWithConstants","\u0001">(var8.kS));
      } else if (var12 == 4101) {
         String var25 = "";
         long var32 = var8.getOperandValue();

         for (long var41 = 0L; var32 != 0L; var41++) {
            if ((var32 & 1L) != 0L) {
               if (!var25.isEmpty()) {
                  var25 = "," + var25;
               }

               var25 = var41 + var25;
            }

            var32 >>>= 1;
         }

         var7.brace();
         var7.append(var25);
         var7.braceClose();
      } else {
         super.formatOperand(var1, var3, var4, var5, var6, var7);
      }

      if (var9) {
         var7.append("|");
      }

      String var26 = "";

      for (qt var37 : var8.UT) {
         if (!this.pC(var37)) {
            var26 = var26 + "." + var37.A;
         }
      }

      if (!var26.isEmpty()) {
         var7.append(var26);
      }

      if (!var10 && var8.E != null) {
         for (oP var38 : var8.E) {
            if (!this.pC(var38)) {
               var7.append(" ");
               this.pC(var1, var3, var38, 0, var6 + 1, var7);
            }
         }
      }
   }

   private boolean pC(oP var1) {
      return var1.pC && !this.getCachedBooleanProperty("DisplayHiddenAttributes");
   }

   private boolean pC(qt var1) {
      return var1.kS && !this.getCachedBooleanProperty("DisplayHiddenAttributes");
   }
}
