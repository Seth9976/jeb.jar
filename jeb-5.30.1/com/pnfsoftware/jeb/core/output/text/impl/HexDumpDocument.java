package com.pnfsoftware.jeb.core.output.text.impl;

import com.pnfsoftware.jeb.client.S;
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
   private SeekableByteChannel channel;
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

      try {
         this.channel = var1.getChannel();
      } catch (IOException var4) {
         logger.error(S.L("Hex.dump: Cannot read input: %s"), var4.getMessage());
      }
   }

   public HexDumpDocument(IInput var1) {
      this(var1, null);
   }

   @Override
   public void dispose() {
      if (this.channel != null) {
         try {
            this.channel.close();
         } catch (IOException var2) {
            logger.catching(var2);
         }

         this.channel = null;
      }
   }

   public IInput getInput() {
      return this.input;
   }

   private byte readByte(long var1) throws InterruptedException {
      return this.readByte(var1, true);
   }

   private byte readByte(long var1, boolean var3) throws InterruptedException {
      if (this.channel == null) {
         return 0;
      } else {
         try {
            this.channel.position(var1);
            this.tempBuffer.clear();
            this.channel.read(this.tempBuffer);
            return this.tempBuffer.get(0);
         } catch (IOException var7) {
            if (Thread.currentThread().isInterrupted()) {
               throw new InterruptedException("Thread is interrupted");
            } else if (var3) {
               try {
                  this.channel = this.input.getChannel();
               } catch (IOException var6) {
                  logger.error(S.L("Input is no more readable. Was it deleted or moved?"));
                  logger.catching(var6);
                  throw new InterruptedException("Input can not be found anymore");
               }

               return this.readByte(var1, false);
            } else {
               Object[] var10000 = new Object[]{var1};
               logger.catching(var7);
               return 0;
            }
         }
      }
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

      while (var5 < var7) {
         long var11 = var5 * bytesPerLine;
         int var13 = bytesPerLine;
         if (var11 > this.docsize) {
            var10.add(new Anchor(var5, var9.size()));
            var9.add(new Line(""));
            var5++;
         } else {
            if (var11 + var13 > this.docsize) {
               var13 = (int)(this.docsize - var11);
            }

            ArrayList var14 = new ArrayList();
            StringBuilder var15 = new StringBuilder();
            var14.add(new TextItem(var15.length(), this.addrCharLen, ItemClassIdentifiers.ADDRESS));
            if (this.addrCharLen == 8) {
               var15.append(Formatter.toHexString(var11, true, 8)).append("  ");
            } else if (this.addrCharLen == 16) {
               var15.append(Formatter.toHexString(var11, true, 16)).append("  ");
            } else {
               if (this.addrCharLen != 17) {
                  throw new RuntimeException();
               }

               var15.append(Formatter.toHexString(var11 >> 32 & -1L, true, 8)).append("'");
               var15.append(Formatter.toHexString(var11 & -1L, true, 8)).append("  ");
            }

            byte[] var17 = new byte[var13];

            int var16;
            for (var16 = 0; var16 < var13; var16++) {
               try {
                  var17[var16] = this.readByte(var11 + var16);
               } catch (InterruptedException var20) {
                  break;
               }

               var14.add(new TextItem(var15.length(), 2, ItemClassIdentifiers.TYPE_BYTE));
               var15.append(Formatter.toHexString(var17[var16] & 255, true, 2)).append(" ");
            }

            int var18;
            for (var18 = var16; var16 < 16; var16++) {
               var15.append("   ");
            }

            var15.append(" ");

            for (int var21 = 0; var21 < var18; var21++) {
               var14.add(new TextItem(var15.length(), 1, ItemClassIdentifiers.TYPE_BYTE));
               if (Characters.isAsciiChar(var17[var21])) {
                  var15.append((char)var17[var21]);
               } else {
                  var15.append(".");
               }
            }

            String var19 = var15.toString();
            var10.add(new Anchor(var5, var9.size()));
            var9.add(new Line(var19, var14));
            var5++;
         }
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
