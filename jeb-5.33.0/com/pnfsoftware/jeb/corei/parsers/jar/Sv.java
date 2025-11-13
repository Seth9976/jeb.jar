package com.pnfsoftware.jeb.corei.parsers.jar;

import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IArchiveUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.corei.parsers.classfile.Ws;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.io.File;
import java.io.InputStream;

@Ser
public class Sv extends AbstractBinaryUnit implements IArchiveUnit {
   private static final ILogger pC = GlobalLog.getLogger(Sv.class);

   public Sv(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super("application/zip", var2, "jar", var1, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      try {
         File var1;
         if (this.getInput() instanceof FileInput) {
            var1 = ((FileInput)this.getInput()).getFile();
         } else {
            try (InputStream var2 = this.getInput().getStream()) {
               var1 = IO.createTempFile(null, ".jar");
               var1.deleteOnExit();
               IO.writeFile(var1, IO.readInputStream(var2));
            }
         }

         IEnginesContext var9 = JebCoreService.getExistingInstance().getDefaultEnginesContexts();
         IRuntimeProject var3 = RuntimeProjectUtil.findProject(this);
         File var4 = Ws.pC(var9, var3).wS(var1);
         IUnit var5 = this.getUnitProcessor().process(this.getName() + ".apk", new BytesInput(IO.readFile(var4)), this, "apk", true, true);
         if (var5 != null) {
            this.addChild(var5);
         }

         return true;
      } catch (Exception var8) {
         pC.catching(var8);
         return false;
      }
   }
}
