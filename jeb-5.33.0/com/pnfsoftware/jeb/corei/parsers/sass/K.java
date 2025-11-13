package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AbstractEnginesPlugin;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.ckx;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;

public class K extends AbstractEnginesPlugin {
   private static final ILogger pC = GlobalLog.getLogger(K.class);
   private static final String A = ckx.pC(
      new byte[]{37, 18, 18, 0, 44, 57, 7, 21, 22, 11, 7, 49, 60, 12, 1, 26, 21, 8, 7, 11, 1, 59, 58, 29, 25, 12, 4, 7, 11, 1}, 1, 86
   );
   private IEnginesContext kS;
   private IEventListener wS;

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("CUDA fatbin processor"),
         S.L("Find and process cubin (SASS binaries) embedded in CUDA fatbin executables (ELF or PE files)."),
         "PNF Software",
         Version.create(1, 0, 0)
      );
   }

   @Override
   public void load(IEnginesContext var1) {
      this.kS = var1;
      this.wS = new Ws(this);
      var1.addListener(this.wS);
   }

   @Override
   public void dispose() {
      if (this.wS != null) {
         this.kS.removeListener(this.wS);
         this.wS = null;
      }
   }

   @Override
   public void execute(IEnginesContext var1, Map var2) {
      pC.debug(S.L("This plugin is auto-executed on any newly created ELF or PE unit."));
   }

   private int pC(ICodeObjectUnit var1) {
      if (!var1.isProcessed()) {
         return -1;
      } else if (Boolean.TRUE.equals(var1.getData(A))) {
         return -2;
      } else {
         var1.setData(A, true, true);
         int var2 = 0;
         HashSet var3 = new HashSet();

         for (ISegmentInformation var5 : var1.getValidSegments()) {
            long var6 = var5.getOffsetInFile();
            long var8 = var5.getSizeInFile();
            if (var3.add(var6) && this.pC(var1, var6, var8, var2)) {
               var2++;
            }
         }

         for (ISegmentInformation var11 : var1.getValidSections()) {
            long var12 = var11.getOffsetInFile();
            long var13 = var11.getSizeInFile();
            if (var3.add(var12) && this.pC(var1, var12, var13, var2)) {
               var2++;
            }
         }

         return var2;
      }
   }

   private boolean pC(ICodeObjectUnit var1, long var2, long var4, int var6) {
      try {
         boolean var11;
         try (InputStream var7 = var1.getInput().getStream()) {
            var7.skipNBytes(var2);
            if (!Sv.pC(var7)) {
               return false;
            }

            BytesInput var8 = new BytesInput(var1.getInput(), var2, (int)var4);
            String var9 = "fatbin" + (var6 == 0 ? "" : var6);
            IUnit var10 = var1.getUnitProcessor().process(var9, var8, var1, null, true, true);
            var1.addChild(var10);
            var11 = true;
         }

         return var11;
      } catch (Exception var14) {
         if (Licensing.isDebugBuild()) {
            throw new RuntimeException(var14);
         } else {
            return false;
         }
      }
   }
}
