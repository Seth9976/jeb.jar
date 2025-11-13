package com.pnfsoftware.jeb.core.output.text.impl;

import com.pnfsoftware.jeb.core.input.FileInputRegionInformation;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.input.IInputLocation;
import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.CoordinatesConversionPrecision;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.units.IInteractiveUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Characters;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;

@SerDisabled
public class HexDumpDocument extends AbstractTextDocument {
   private static final ILogger logger = GlobalLog.getLogger(HexDumpDocument.class);
   private static int bytesPerAnchor = 16;
   private static int bytesPerLine = 16;
   private IInput input;
   private IUnit unit;
   private long docsize;
   private int addrCharLen;
   private long anchorRange;
   private ByteBuffer tempBuffer = ByteBuffer.allocate(1);

   public HexDumpDocument(IInput var1, IUnit var2) {
      this.input = var1;
      this.unit = var2;
      this.docsize = var1.getCurrentSize();
      this.addrCharLen = this.docsize <= 2147483647L ? 8 : 16;
      this.anchorRange = (this.docsize + bytesPerLine - 1L) / bytesPerLine;
      if (this.anchorRange == 0L) {
         this.anchorRange = 1L;
      }
   }

   public HexDumpDocument(IInput var1) {
      this(var1, null);
   }

   @Override
   public void dispose() {
   }

   public IInput getInput() {
      return this.input;
   }

   @Override
   public long getAnchorCount() {
      return this.anchorRange;
   }

   @Override
   public ITextDocumentPart getDocumentPart(long var1, int var3, int var4) {
      if (var4 < 100) {
         var4 = 100;
      }

      if (var3 < 600) {
         var3 = 600;
      }

      long var5 = var1 - var4;
      if (var5 < 0L) {
         var5 = 0L;
      }

      long var7 = var1 + var3;
      if (var7 > this.anchorRange) {
         var7 = this.anchorRange;
      }

      ArrayList var9 = new ArrayList();
      ArrayList var10 = new ArrayList();

      try (SeekableByteChannel var11 = this.input.getChannel()) {
         while (var5 < var7) {
            long var12 = var5 * bytesPerLine;
            int var14 = bytesPerLine;
            if (var12 > this.docsize) {
               var10.add(new Anchor(var5, var9.size()));
               var9.add(new Line(""));
               var5++;
            } else {
               if (var12 + var14 > this.docsize) {
                  var14 = (int)(this.docsize - var12);
               }

               ArrayList var15 = new ArrayList();
               StringBuilder var16 = new StringBuilder();
               var15.add(new TextItem(var16.length(), this.addrCharLen, ItemClassIdentifiers.ADDRESS));
               if (this.addrCharLen == 8) {
                  var16.append(Formatter.toHexString(var12, true, 8)).append("  ");
               } else if (this.addrCharLen == 16) {
                  var16.append(Formatter.toHexString(var12, true, 16)).append("  ");
               } else {
                  if (this.addrCharLen != 17) {
                     throw new RuntimeException();
                  }

                  var16.append(Formatter.toHexString(var12 >> 32 & -1L, true, 8)).append("'");
                  var16.append(Formatter.toHexString(var12 & -1L, true, 8)).append("  ");
               }

               byte[] var18 = new byte[var14];

               int var17;
               for (var17 = 0; var17 < var14; var17++) {
                  try {
                     this.tempBuffer.position(0);
                     var11.position(var12 + var17);
                     var11.read(this.tempBuffer);
                     var18[var17] = this.tempBuffer.get(0);
                  } catch (IOException var22) {
                     var18[var17] = 0;
                  }

                  var15.add(new TextItem(var16.length(), 2, ItemClassIdentifiers.TYPE_BYTE));
                  var16.append(Formatter.toHexString(var18[var17] & 255, true, 2)).append(" ");
               }

               int var19;
               for (var19 = var17; var17 < 16; var17++) {
                  var16.append("   ");
               }

               var16.append(" ");

               for (int var25 = 0; var25 < var19; var25++) {
                  var15.add(new TextItem(var16.length(), 1, ItemClassIdentifiers.TYPE_BYTE));
                  if (Characters.isAsciiChar(var18[var25])) {
                     var16.append((char)var18[var25]);
                  } else {
                     var16.append(".");
                  }
               }

               String var20 = var16.toString();
               var10.add(new Anchor(var5, var9.size()));
               var9.add(new Line(var20, var15));
               var5++;
            }
         }
      } catch (IOException var24) {
         logger.catching(var24);
      }

      return new TextDocumentPart(var9, var10);
   }

   @Override
   public String coordinatesToAddress(ICoordinates var1, AddressConversionPrecision var2) {
      long var3 = var1.getAnchorId() * bytesPerAnchor + var1.getLineDelta() * bytesPerLine;
      int var5 = var1.getColumnOffset();
      if (var5 >= this.addrCharLen + 2 && var5 < this.addrCharLen + 67 && var5 != this.addrCharLen + 50) {
         long var6;
         if (var5 >= this.addrCharLen + 51) {
            var6 = var3 + (var5 - (this.addrCharLen + 51));
         } else {
            int var8 = (var5 - (this.addrCharLen + 2)) % 3;
            if (var8 == 2) {
               return null;
            }

            var6 = var3 + (var5 - (this.addrCharLen + 2)) / 3;
         }

         if (var6 > this.docsize) {
            var6 = this.docsize;
         }

         return Strings.ff("@%Xh", var6);
      } else {
         return null;
      }
   }

   @Override
   public ICoordinates addressToCoordinates(String var1, CoordinatesConversionPrecision var2) {
      if (var1 != null && !var1.isEmpty()) {
         long var3 = -1L;
         if (var1.charAt(0) != '@' && this.unit instanceof IInteractiveUnit) {
            IInputLocation var5 = ((IInteractiveUnit)this.unit).addressToLocation(var1);
            if (var5 instanceof FileInputRegionInformation) {
               var3 = ((FileInputRegionInformation)var5).getOffset();
            }
         }

         if (var3 == -1L) {
            if (var1.charAt(0) == '@') {
               var1 = var1.substring(1);
            }

            var3 = Conversion.stringToLong(var1, -1L);
         }

         return var3 >= 0L && var3 <= this.docsize ? new Coordinates(var3 / bytesPerLine, 0, (int)(this.addrCharLen + 2 + 3L * (var3 % bytesPerLine))) : null;
      } else {
         return null;
      }
   }
}
