package com.pnfsoftware.jeb.core.output.text.impl;

import com.pnfsoftware.jeb.core.exceptions.NotImplementedException;
import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.CoordinatesConversionPrecision;
import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocument;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.output.text.TextDocumentUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.util.events.EventSource;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.List;

@Ser
public abstract class AbstractTextDocument extends EventSource implements ITextDocument {
   @Override
   public void dispose() {
   }

   @Override
   public IPropertyManager getPropertyManager() {
      return null;
   }

   @Override
   public long getInitialAnchor() {
      return this.getFirstAnchor();
   }

   @Override
   public long getFirstAnchor() {
      return 0L;
   }

   @Override
   public ITextDocumentPart getDocumentPart(long var1, int var3) {
      return this.getDocumentPart(var1, var3, 0);
   }

   @Override
   public ITextDocumentPart getDocumentPart2(long var1, long var3) throws NotImplementedException {
      throw new NotImplementedException("getDocumentPart2() is not implemented in this document object: " + this.getClass().getSimpleName());
   }

   protected boolean useLineDelta() {
      return true;
   }

   protected boolean useDisplayLineNumber() {
      return false;
   }

   private int getAnchorDelta() {
      return this.useDisplayLineNumber() ? 1 : 0;
   }

   @Override
   public final String coordinatesToAddress(ICoordinates var1) {
      return this.coordinatesToAddress(var1, AddressConversionPrecision.DEFAULT);
   }

   @Override
   public String coordinatesToAddress(ICoordinates var1, AddressConversionPrecision var2) {
      if (var1 == null) {
         return null;
      } else {
         return !this.useLineDelta()
            ? Strings.ff("%d,%d", var1.getAnchorId() + this.getAnchorDelta(), var1.getColumnOffset())
            : Strings.ff("%d,%d,%d", var1.getAnchorId() + this.getAnchorDelta(), var1.getLineDelta(), var1.getColumnOffset());
      }
   }

   @Override
   public final ICoordinates addressToCoordinates(String var1) {
      return this.addressToCoordinates(var1, CoordinatesConversionPrecision.BEST);
   }

   @Override
   public ICoordinates addressToCoordinates(String var1, CoordinatesConversionPrecision var2) {
      if (var1 == null) {
         return null;
      } else {
         List var3 = parseLongList(var1);
         if (var3 == null) {
            return null;
         } else if (var3.size() == 1) {
            return new Coordinates((Long)var3.get(0) - this.getAnchorDelta());
         } else if (var3.size() == 2) {
            return !this.useLineDelta()
               ? new Coordinates((Long)var3.get(0) - this.getAnchorDelta(), 0, ((Long)var3.get(1)).intValue())
               : new Coordinates((Long)var3.get(0) - this.getAnchorDelta(), ((Long)var3.get(1)).intValue());
         } else {
            return var3.size() == 3
               ? new Coordinates((Long)var3.get(0) - this.getAnchorDelta(), ((Long)var3.get(1)).intValue(), ((Long)var3.get(2)).intValue())
               : null;
         }
      }
   }

   private static List parseLongList(String var0) {
      var0 = var0.trim();
      if (var0.startsWith("@")) {
         var0 = var0.substring(1).trim();
      }

      if (var0.startsWith("(") && var0.endsWith(")")) {
         var0 = var0.substring(1, var0.length() - 1).trim();
      }

      ArrayList var1 = new ArrayList();

      for (String var5 : var0.split(",")) {
         try {
            long var6 = Long.parseLong(var5.trim());
            var1.add(var6);
         } catch (NumberFormatException var8) {
            return null;
         }
      }

      return var1;
   }

   @Override
   public String toString() {
      return super.toString();
   }

   @Override
   public String format() {
      return TextDocumentUtil.getText(this);
   }
}
