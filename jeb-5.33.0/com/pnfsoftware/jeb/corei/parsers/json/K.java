package com.pnfsoftware.jeb.corei.parsers.json;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IJsonUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.encoding.json.parser.JSONParser;
import com.pnfsoftware.jeb.util.encoding.json.parser.ParseException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

@Ser
public class K extends AbstractBinaryUnit implements IJsonUnit {
   private static final ILogger pC = GlobalLog.getLogger(K.class);
   @SerTransient
   private Object A;

   public K(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super("application/json", var2, "json", var1, var3, var4, var5);
   }

   @Override
   public Object getDocument() {
      this.process();
      return this.A;
   }

   @Override
   public boolean isProcessed() {
      return super.isProcessed() && this.A != null;
   }

   private int pC() {
      try {
         int var2;
         try (InputStream var1 = this.getInput().getStream()) {
            var2 = Strings.getInitialBlankSize(var1, true);
         }

         return var2;
      } catch (IOException var6) {
         return 0;
      }
   }

   @Override
   protected boolean processInternal() {
      int var2 = this.pC();

      String var1;
      try (K.Av var3 = new K.Av(this.getInput().getStream())) {
         byte[] var4 = IO.readInputStream(var3);
         String var5 = Strings.readBOM(var4);
         if (var5 == null) {
            var5 = "UTF-8";
         }

         try {
            var1 = new String(var4, var2, var4.length - var2, var5);
         } catch (UnsupportedEncodingException var8) {
            var1 = new String(var4, var2, var4.length - var2, "UTF-8");
         }
      } catch (IOException var10) {
         return false;
      }

      JSONParser var11 = new JSONParser();

      try {
         this.A = var11.parse(var1, new Ws(this));
         return true;
      } catch (ParseException var7) {
         pC.debug(var7.toString());
         return false;
      }
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new bO(this, 1L, S.L("Formatted Text"), true), false);
      }

      return var1;
   }

   private static class Av extends FilterInputStream {
      protected Av(InputStream var1) {
         super(var1);
      }
   }
}
