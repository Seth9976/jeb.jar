package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.CoordinatesConversionPrecision;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.output.text.impl.AbstractTextDocument;
import com.pnfsoftware.jeb.core.output.text.impl.Anchor;
import com.pnfsoftware.jeb.core.output.text.impl.Coordinates;
import com.pnfsoftware.jeb.core.output.text.impl.Line;
import com.pnfsoftware.jeb.core.output.text.impl.TextDocumentPart;
import com.pnfsoftware.jeb.core.output.text.impl.TextItem;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerTargetInformation;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Characters;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;

@SerDisabled
public class Cg extends AbstractTextDocument {
   private static final ILogger sY = GlobalLog.getLogger(Cg.class);
   IDebuggerUnit pC;
   long A;
   int kS;
   int wS;
   int UT;
   int E = 16;

   public Cg(IDebuggerUnit var1) throws JebException {
      this.pC = var1;
      IDebuggerTargetInformation var2 = var1.getTargetInformation();
      if (var2 != null) {
         ProcessorType var3 = var2.getProcessorType();
         if (var3 != null) {
            if (var3 == ProcessorType.ARM || var3 == ProcessorType.X86 || var3 == ProcessorType.MIPS) {
               this.kS = 4096;
               this.A = 4294967296L;
            } else if (var3 == ProcessorType.ARM64 || var3 == ProcessorType.X86_64 || var3 == ProcessorType.MIPS64) {
               this.kS = 4096;
               this.A = Long.MAX_VALUE;
            }
         }
      }

      if (this.kS != 0 && this.A != 0L) {
         this.wS = 0;
         this.UT = 16;
      } else {
         throw new JebException("Cannot render memory view for target: " + var2);
      }
   }

   @Override
   public IUnit getUnit() {
      return this.pC;
   }

   @Override
   public long getAnchorCount() {
      return this.A;
   }

   @Override
   public ITextDocumentPart getDocumentPart(long var1, int var3, int var4) {
      if (this.wS != 0) {
         throw new RuntimeException();
      } else {
         ArrayList var5 = new ArrayList();
         ArrayList var6 = new ArrayList();
         int var7 = var4 * this.E;
         long var8 = var1 - var7;
         if (var8 < 0L) {
            var8 = 0L;
         }

         int var10 = var3 * this.E;
         long var11 = var1 + var10;
         byte[] var13 = new byte[this.kS];

         while (var8 < var11) {
            long var14 = var8 + this.kS & ~(this.kS - 1);
            int var16 = (int)(var14 - var8);
            if (var14 > var11) {
               var16 = (int)(var11 - var8);
            }

            int var17 = this.pC.readMemory(var8, var16, var13, 0);
            int var18 = 0;
            if (var17 <= 0) {
               var17 = var16;

               while (var18 < var17) {
                  int var25 = this.E;
                  ArrayList var26 = new ArrayList();
                  StringBuilder var27 = new StringBuilder();
                  var26.add(new TextItem(var27.length(), this.UT, ItemClassIdentifiers.ADDRESS));
                  if (this.UT == 8) {
                     Strings.ff(var27, "%08X  ", var8 + var18);
                  } else {
                     if (this.UT != 16) {
                        throw new RuntimeException();
                     }

                     Strings.ff(var27, "%016X  ", var8 + var18);
                  }

                  int var29;
                  for (var29 = 0; var29 < var25; var29++) {
                     var27.append("?? ");
                  }

                  int var31;
                  for (var31 = var29; var29 < 16; var29++) {
                     var27.append("   ");
                  }

                  var27.append(" ");

                  for (int var30 = 0; var30 < var31; var30++) {
                     var27.append(".");
                  }

                  String var33 = var27.toString();
                  var6.add(new Anchor(var8 + var18, var5.size()));
                  var5.add(new Line(var33, var26));
                  var18 += var25;
               }
            } else {
               while (var18 < var17) {
                  int var19 = this.E;
                  ArrayList var20 = new ArrayList();
                  StringBuilder var21 = new StringBuilder();
                  var20.add(new TextItem(var21.length(), this.UT, ItemClassIdentifiers.ADDRESS));
                  if (this.UT == 8) {
                     Strings.ff(var21, "%08X  ", var8 + var18);
                  } else {
                     if (this.UT != 16) {
                        throw new RuntimeException();
                     }

                     Strings.ff(var21, "%016X  ", var8 + var18);
                  }

                  int var22;
                  for (var22 = 0; var22 < var19; var22++) {
                     var20.add(new TextItem(var21.length(), 2, ItemClassIdentifiers.TYPE_BYTE));
                     Strings.ff(var21, "%02X ", var13[var18 + var22]);
                  }

                  int var23;
                  for (var23 = var22; var22 < 16; var22++) {
                     var21.append("   ");
                  }

                  var21.append(" ");

                  for (int var28 = 0; var28 < var23; var28++) {
                     var20.add(new TextItem(var21.length(), 1, ItemClassIdentifiers.TYPE_BYTE));
                     byte var24 = var13[var18 + var28];
                     if (Characters.isAsciiChar(var24)) {
                        var21.append((char)var24);
                     } else {
                        var21.append(".");
                     }
                  }

                  String var32 = var21.toString();
                  var6.add(new Anchor(var8 + var18, var5.size()));
                  var5.add(new Line(var32, var20));
                  var18 += var19;
               }
            }

            var8 += var17;
         }

         return new TextDocumentPart(var5, var6);
      }
   }

   @Override
   public String coordinatesToAddress(ICoordinates var1, AddressConversionPrecision var2) {
      if (var1 == null) {
         return null;
      } else {
         long var3 = var1.getAnchorId();
         var3 += var1.getLineDelta() * this.E;
         var3 += (var1.getColumnOffset() - 18) / 3;
         return Strings.ff("%Xh", var3);
      }
   }

   @Override
   public ICoordinates addressToCoordinates(String var1, CoordinatesConversionPrecision var2) {
      if (var1 == null) {
         return null;
      } else {
         long var3 = Conversion.stringToLong(var1, -1L);
         if (var3 != -1L) {
            long var5 = var3 / 16L * 16L;
            int var7 = 18 + 3 * (int)(var3 % 16L);
            return new Coordinates(var5, 0, var7);
         } else {
            return null;
         }
      }
   }
}
