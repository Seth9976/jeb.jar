package com.pnfsoftware.jeb.corei.parsers.utf8;

import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.CoordinatesConversionPrecision;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.output.text.impl.AbstractTextDocument;
import com.pnfsoftware.jeb.core.output.text.impl.Anchor;
import com.pnfsoftware.jeb.core.output.text.impl.Coordinates;
import com.pnfsoftware.jeb.core.output.text.impl.Line;
import com.pnfsoftware.jeb.core.output.text.impl.TextItem;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.List;

@SerDisabled
public class nI extends AbstractTextDocument {
   private static final ILogger q = GlobalLog.getLogger(nI.class);
   private tw RF;
   private static final int xK = 20;
   private int Dw;

   public nI(tw var1) {
      this.RF = var1;
      List var2 = var1.q();
      this.Dw = (var2.size() + 20 - 1) / 20;
   }

   public tw q() {
      return this.RF;
   }

   @Override
   public ICoordinates addressToCoordinates(String var1, CoordinatesConversionPrecision var2) {
      String[] var3 = var1.split(",");
      if (var3.length != 2) {
         return null;
      } else {
         int var4;
         int var5;
         try {
            var4 = Integer.parseInt(var3[0]);
            var5 = Integer.parseInt(var3[1]);
         } catch (Exception var6) {
            return null;
         }

         return new Coordinates(var4 / 20, var4 % 20, var5);
      }
   }

   @Override
   public String coordinatesToAddress(ICoordinates var1, AddressConversionPrecision var2) {
      StringBuilder var3 = new StringBuilder();
      var3.append(var1.getAnchorId() * 20L + var1.getLineDelta()).append(',');
      var3.append(var1.getColumnOffset());
      return var3.toString();
   }

   @Override
   public long getAnchorCount() {
      return this.Dw;
   }

   @Override
   public ITextDocumentPart getDocumentPart(long var1, int var3, int var4) {
      int var5 = (int)var1;
      if (var5 >= 0 && var3 >= 0 && var4 >= 0) {
         q.info("Requesting lines: id=%d, after=%d, before=%d", var5, var3, var4);
         int var6 = var5 - (var4 + 20 - 1) / 20;
         int var7 = var5 + var3 / 20;
         if (var6 < 0) {
            var6 = 0;
         }

         if (var7 >= this.Dw) {
            var7 = this.Dw - 1;
         }

         ArrayList var8 = new ArrayList();
         ArrayList var9 = new ArrayList();
         List var10 = this.RF.q();
         int var11 = var6 * 20;
         int var12 = 0;

         for (int var13 = var6; var13 <= var7; var13++) {
            Anchor var14 = new Anchor(var13, var12);
            var8.add(var14);
            int var15 = 0;

            for (int var16 = var11; var16 < var11 + 20 && var16 < var10.size(); var16++) {
               String var17 = (String)var10.get(var16);
               ArrayList var18 = new ArrayList();
               if (var17.length() >= 10) {
                  var18.add(new TextItem(0, 5, ItemClassIdentifiers.CLASS_NAME, 1L, 0));
               }

               var9.add(new Line(var17, var18));
               var15++;
            }

            var11 += var15;
            var12 += var15;
         }

         return new ej(var8, var9);
      } else {
         throw new IllegalArgumentException();
      }
   }
}
