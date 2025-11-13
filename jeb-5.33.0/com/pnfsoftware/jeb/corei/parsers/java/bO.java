package com.pnfsoftware.jeb.corei.parsers.java;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.encoding.jflex.lexers.JavaLexer;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Ser
public class bO extends AbstractBinaryUnit implements K {
   private static final ILogger pC = GlobalLog.getLogger(bO.class);
   @SerTransient
   private String A;
   @SerTransient
   private List kS;

   public bO(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super("text/plain", var2, "java", var1, var3, var4, var5);
   }

   @Override
   public String pC() {
      return this.A;
   }

   @Override
   public List A() {
      this.process();
      return this.kS;
   }

   @Override
   public boolean isProcessed() {
      return super.isProcessed() && this.kS != null;
   }

   @Override
   protected boolean processInternal() {
      try (InputStream var1 = this.getInput().getStream()) {
         this.A = Strings.decodeUTF8(IO.readInputStream(var1));
      } catch (IOException var8) {
         return false;
      }

      JavaLexer var9 = new JavaLexer();

      try {
         this.kS = var9.parse(this.A);
         return true;
      } catch (IOException var5) {
         pC.catchingSilent(var5);
         return false;
      } catch (Exception var6) {
         return false;
      }
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new cq(this, 1L, S.L("Formatted Text"), true), false);
      }

      return var1;
   }
}
