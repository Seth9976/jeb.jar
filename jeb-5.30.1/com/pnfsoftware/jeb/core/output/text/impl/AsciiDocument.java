package com.pnfsoftware.jeb.core.output.text.impl;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.CoordinatesConversionPrecision;
import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Characters;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

@SerDisabled
public class AsciiDocument extends AbstractTextDocument {
   private static final ILogger logger = GlobalLog.getLogger(AsciiDocument.class);
   private IInput input;
   private List lineStarts;
   private long anchorRange;
   private long totalSize;

   public AsciiDocument(IInput var1) {
      this.input = var1;
      this.lineStarts = new ArrayList();
      this.lineStarts.add(0L);
      long var2 = 0L;

      try (InputStream var4 = var1.getStream()) {
         byte[] var6 = new byte[16384];

         int var5;
         while ((var5 = var4.read(var6, 0, var6.length)) != -1) {
            for (int var7 = 0; var7 < var5; var7++) {
               byte var8 = var6[var7];
               if (var8 == 10 || var8 == 13 && (var7 + 1 >= var5 || var6[var7 + 1] != 10)) {
                  this.lineStarts.add(var2 + var7 + 1L);
               }
            }

            var2 += var5;
         }
      } catch (IOException var11) {
         logger.catching(var11);
      }

      this.anchorRange = this.lineStarts.size();
      this.totalSize = var2;
   }

   @Override
   public long getAnchorCount() {
      return this.anchorRange;
   }

   @Override
   public ITextDocumentPart getDocumentPart(long var1, int var3, int var4) {
      if (var1 > 2147483647L) {
         throw new RuntimeException("");
      } else {
         int var5 = (int)var1 - var4;
         if (var5 < 0) {
            var5 = 0;
         }

         long var6 = var1 + var3;
         if (var6 > this.anchorRange) {
            var6 = this.anchorRange;
         }

         ArrayList var8 = new ArrayList();
         ArrayList var9 = new ArrayList();
         ByteBuffer var10 = ByteBuffer.allocate(81920);
         var10.order(ByteOrder.LITTLE_ENDIAN);
         if (var5 < var6) {
            try (SeekableByteChannel var11 = this.input.getChannel()) {
               var11.position((Long)this.lineStarts.get(var5));

               while (var5 < var6) {
                  long var12 = (Long)this.lineStarts.get(var5);
                  long var14 = var5 + 1 >= this.lineStarts.size() ? this.totalSize : (Long)this.lineStarts.get(var5 + 1);
                  int var16 = (int)(var14 - var12);
                  if (var16 < 0) {
                     break;
                  }

                  if (var16 > var10.capacity()) {
                     int var17 = (var16 & -4096) + 4096;
                     var10 = ByteBuffer.allocate(var17);
                     var10.order(ByteOrder.LITTLE_ENDIAN);
                  }

                  var10.position(0);
                  var10.limit(var16);
                  int var24 = var11.read(var10);
                  StringBuilder var18 = new StringBuilder();

                  for (int var19 = 0; var19 < var24; var19++) {
                     byte var20 = var10.get(var19);
                     if (!Characters.isAsciiChar(var20) && var20 != 9) {
                        if (var20 == 10 || var20 == 13) {
                           break;
                        }

                        Strings.ff(var18, "\\x%02X", var20 & 255);
                     } else {
                        var18.append((char)var20);
                     }
                  }

                  var9.add(new Anchor(var5, var8.size()));
                  var8.add(new Line(var18));
                  var5++;
               }
            } catch (IOException var23) {
               logger.catching(var23);
            }
         }

         return new TextDocumentPart(var8, var9);
      }
   }

   @Override
   protected boolean useLineDelta() {
      return false;
   }

   @Override
   protected boolean useDisplayLineNumber() {
      return true;
   }

   @Override
   public String coordinatesToAddress(ICoordinates var1, AddressConversionPrecision var2) {
      String var3 = super.coordinatesToAddress(var1, var2);
      return var3 == null ? null : "@" + var3;
   }

   @Override
   public ICoordinates addressToCoordinates(String var1, CoordinatesConversionPrecision var2) {
      return var1 != null && !var1.isEmpty() && var1.charAt(0) == '@' ? super.addressToCoordinates(var1.substring(1), var2) : null;
   }
}
