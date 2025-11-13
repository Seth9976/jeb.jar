package com.pnfsoftware.jeb.corei.parsers.classfile;

import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.io.File;
import java.io.InputStream;

@Ser
public class ej extends AbstractBinaryUnit {
   private static final ILogger q = GlobalLog.getLogger(ej.class);

   public ej(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "javaclass", var1, var3, var4, var5);
   }

   public boolean q() {
      return this.getPropertyManager().getBoolean("UseD8ForDexConversion");
   }

   public static oM q(IEnginesContext var0, IRuntimeProject var1) {
      IPropertyManager var2;
      if (var1 != null) {
         var2 = var1.getPropertyManager();
      } else {
         var2 = var0.getPropertyManager();
      }

      String var3 = UnitUtil.unitProperty(var0, "javaclass", "UseD8ForDexConversion");
      boolean var4 = var2.getBoolean(var3);
      var3 = UnitUtil.unitProperty(var0, "javaclass", "MinimumAPILevel");
      int var5 = var2.getInteger(var3);
      var3 = UnitUtil.unitProperty(var0, "javaclass", "EnableDesugaring");
      boolean var6 = var2.getBoolean(var3);
      return new oM(var4, var5, var6);
   }

   @Override
   protected boolean processInternal() {
      try {
         File var1;
         if (this.getInput() instanceof FileInput) {
            var1 = ((FileInput)this.getInput()).getFile();
         } else {
            try (InputStream var2 = this.getInput().getStream()) {
               var1 = IO.createTempFile(null, ".class");
               var1.deleteOnExit();
               IO.writeFile(var1, IO.readInputStream(var2));
            }
         }

         IEnginesContext var9 = JebCoreService.getExistingInstance().getDefaultEnginesContexts();
         IRuntimeProject var3 = RuntimeProjectUtil.findProject(this);
         File var4 = q(var9, var3).xK(var1);
         IUnit var5 = this.getUnitProcessor().process(this.getName() + ".dex", new BytesInput(IO.readFile(var4)), this, "dex", true, true);
         if (var5 != null) {
            this.addChild(var5);
         }

         return true;
      } catch (Exception var8) {
         q.catching(var8);
         return false;
      }
   }
}
