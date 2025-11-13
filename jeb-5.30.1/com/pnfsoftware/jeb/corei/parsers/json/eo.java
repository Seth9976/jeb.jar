package com.pnfsoftware.jeb.corei.parsers.json;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.CoordinatesConversionPrecision;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocument;
import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.output.text.impl.Coordinates;
import com.pnfsoftware.jeb.core.units.IJsonUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.util.encoding.json.parser.JsonId;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class eo extends CodeDocument {
   private static final ILogger q = GlobalLog.getLogger(eo.class);
   private nI RF;
   private CU xK;
   private int Dw = 0;

   public eo(nI var1) {
      this.RF = var1;
      this.q((IJsonUnit)var1);
   }

   @Override
   public IUnit getUnit() {
      return this.RF;
   }

   @Override
   public long getAnchorCount() {
      return 1L;
   }

   @Override
   public String coordinatesToAddress(ICoordinates var1, AddressConversionPrecision var2) {
      return String.valueOf(var1.getLineDelta());
   }

   @Override
   public ICoordinates addressToCoordinates(String var1, CoordinatesConversionPrecision var2) {
      try {
         Integer var3 = Integer.parseInt(var1);
         return new Coordinates(0L, var3);
      } catch (NumberFormatException var4) {
         return null;
      }
   }

   @Override
   public ITextDocumentPart getDocumentPart(long var1, int var3, int var4) {
      return this.xK;
   }

   private void q(IJsonUnit var1) {
      this.xK = new CU(0L);
      Object var2 = var1.getDocument();
      this.xK.registerAnchor("wholeDocumentAnchor");
      this.q(var1, var2);
      this.xK.eol();
   }

   void q() {
      this.xK.eol();
      this.xK.space(this.Dw * 2);
   }

   void q(IJsonUnit var1, Object var2) {
      if (var2 instanceof Map var3) {
         if (var3.isEmpty()) {
            this.xK.append("{}");
         } else {
            this.xK.append("{");
            int var4 = 0;

            for (Object var6 : var3.entrySet()) {
               if (var4 == 0) {
                  this.Dw++;
               } else if (var4 >= 1) {
                  this.xK.append(",");
               }

               this.q();
               Entry var7 = (Entry)var6;
               if (var7.getKey() instanceof JsonId) {
                  this.q(var7.getKey(), var1);
               } else {
                  this.q(var7.getKey());
               }

               this.xK.append(": ");
               this.q(var1, var7.getValue());
               var4++;
            }

            this.Dw--;
            this.q();
            this.xK.append("}");
         }
      } else if (var2 instanceof List var8) {
         if (var8.isEmpty()) {
            this.xK.append("[]");
         } else {
            this.xK.append("[");
            int var11 = 0;

            for (Object var13 : var8) {
               if (var11 == 0) {
                  this.Dw++;
               } else if (var11 >= 1) {
                  this.xK.append(",");
               }

               this.q();
               this.q(var1, var13);
               var11++;
            }

            this.Dw--;
            this.q();
            this.xK.append("]");
         }
      } else if (var2 instanceof Boolean) {
         this.xK.appendAndRecord((Boolean)var2 ? "true" : "false", ItemClassIdentifiers.KEYWORD);
      } else if (var2 instanceof Float) {
         boolean var9 = ((Float)var2).isNaN() || ((Float)var2).isInfinite();
         this.xK.appendAndRecord(var9 ? "null" : var2.toString(), ItemClassIdentifiers.NUMBER);
      } else if (var2 instanceof Double) {
         boolean var10 = ((Double)var2).isNaN() || ((Double)var2).isInfinite();
         this.xK.appendAndRecord(var10 ? "null" : var2.toString(), ItemClassIdentifiers.NUMBER);
      } else if (var2 instanceof Integer || var2 instanceof Long || var2 instanceof BigInteger) {
         this.xK.appendAndRecord(var2.toString(), ItemClassIdentifiers.NUMBER);
      } else if (var2 instanceof String) {
         this.q(var2);
      } else if (var2 == null) {
         this.xK.appendAndRecord("null", ItemClassIdentifiers.KEYWORD);
      } else if (var2 instanceof JsonId) {
         this.q(var2, var1);
      } else {
         this.xK.append(var2.toString());
      }
   }

   void q(Object var1) {
      this.q(var1, true);
   }

   void q(Object var1, IUnit var2) {
      var2.getNotificationManager()
         .addNotification(new UnitNotification(NotificationType.CORRUPTION, S.L("Illegal identifier"), String.valueOf(this.xK.getCurrentLineIndex())));
      this.q(var1, false);
   }

   void q(Object var1, boolean var2) {
      String var3 = Formatter.escapeString(String.valueOf(var1));
      if (var2) {
         this.xK.append("\"");
      }

      this.xK.appendAndRecord(var3, ItemClassIdentifiers.STRING);
      if (var2) {
         this.xK.append("\"");
      }
   }
}
