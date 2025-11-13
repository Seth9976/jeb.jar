package com.pnfsoftware.jeb.corei.parsers.utf8;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.actions.ActionContext;
import com.pnfsoftware.jeb.core.actions.ActionRenameData;
import com.pnfsoftware.jeb.core.actions.ActionXrefsData;
import com.pnfsoftware.jeb.core.actions.IActionData;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterAdapter;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractInteractiveBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class rQ extends AbstractInteractiveBinaryUnit {
   private static final ILogger pC = GlobalLog.getLogger(rQ.class);
   @SerId(1)
   private List A = new ArrayList();
   @SerId(2)
   private Map kS = new HashMap();

   public rQ(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "utf8text", var1, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      byte[] var1;
      try (InputStream var2 = this.getInput().getStream()) {
         var1 = IO.readInputStream(var2);
      } catch (IOException var15) {
         return false;
      }

      try (ByteArrayInputStream var16 = new ByteArrayInputStream(var1, 3, var1.length - 3)) {
         this.A = IO.readLines(var16, Charset.forName("UTF-8"));
      } catch (IOException var13) {
         return false;
      }

      for (String var3 : this.A) {
         String[] var4 = var3.split("[!?,\\s]+");

         for (String var8 : var4) {
            if (!var8.isEmpty()) {
               Integer var9 = (Integer)this.kS.get(var8);
               if (var9 == null) {
                  var9 = 0;
               }

               var9 = var9 + 1;
               this.kS.put(var8, var9);
            }
         }
      }

      this.addNotification(new UnitNotification(NotificationType.CORRUPTION, S.L("Line overflow"), "8,7"));
      this.addNotification(new UnitNotification(NotificationType.AREA_OF_INTEREST, S.L("Strange calls, potentially bad"), "foo()"));
      return true;
   }

   public List pC() {
      return this.A;
   }

   public Map A() {
      return this.kS;
   }

   @Override
   public IUnitFormatter getFormatter() {
      Object var1 = super.getFormatter();
      if (((IUnitFormatter)var1).getPresentationCount() == 0) {
         UnitFormatterAdapter var2 = (UnitFormatterAdapter)var1;
         var2.addDocumentPresentation(new zp(this, S.L("Formatted Text"), true));
         var2.addDocumentPresentation(new KD(this, S.L("All Words"), false));
         var2.addDocumentPresentation(new yt(this, S.L("All Words (static)"), false));
         var2.addDocumentPresentation(new RC(this, S.L("Table of Contents"), false));
         var2.addDocumentPresentation(new sy(this, S.L("Table of Contents (Table Tree)"), false));
         var2.addDocumentPresentation(new HE(this, S.L("Additional Bin"), false));
         var2.addDocumentPresentation(new qt(this, S.L("Text View"), false));
         var2.addDocumentPresentation(new oP(this, S.L("Text View"), false));
         var1 = var2;
      }

      return (IUnitFormatter)var1;
   }

   @Override
   public List getItemActions(long var1) {
      ArrayList var3 = new ArrayList();
      if (var1 == 1L) {
         var3.add(2);
         var3.add(4);
      }

      return var3;
   }

   @Override
   public boolean canExecuteAction(ActionContext var1) {
      int var2 = var1.getActionId();
      long var3 = var1.getItemId();
      return this.getItemActions(var3).contains(var2);
   }

   @Override
   public boolean prepareExecution(ActionContext var1, IActionData var2) {
      int var3 = var1.getActionId();
      if (var3 == 2) {
         ((ActionRenameData)var2).setCurrentName("Bla");
         return true;
      } else if (var3 == 4) {
         ((ActionXrefsData)var2).setAddresses(Arrays.asList("0,10", "0,11"));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2) {
      return true;
   }
}
