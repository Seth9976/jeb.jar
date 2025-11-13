package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.client.HeadlessClientContext;
import com.pnfsoftware.jeb.core.Artifact;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.ILiveArtifact;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class oP {
   private static final ILogger pC = GlobalLog.getLogger(oP.class);

   public static HeadlessClientContext pC(K var0) throws JebException {
      HeadlessClientContext var1 = new HeadlessClientContext();
      var1.initialize(new String[0]);
      var1.start();
      IEnginesContext var2 = var1.getEnginesContext();
      String var3 = var0.pC();
      IPropertyManager var4 = var2.getPropertyManager();
      if (var3 != null) {
         String var5 = UnitUtil.unitProperty(var2, var3, "AnalysisStyle");
         int var6 = var0.A();
         var4.setInteger(var5, var6);
         String var7 = UnitUtil.unitProperty(var2, var3, "AdvancedAnalysis");
         int var8 = var0.kS();
         var4.setInteger(var7, var8);
         if (var0.A) {
            String var9 = UnitUtil.unitProperty(var2, var3, "DebugInformationRetrievalPolicy");
            var4.setInteger(var9, 2);
            String var10 = UnitUtil.unitProperty(var2, var3, "DebugInformationUsagePolicy");
            var4.setInteger(var10, 2);
         } else {
            String var11 = UnitUtil.unitProperty(var2, var3, "DebugInformationRetrievalPolicy");
            var4.setInteger(var11, 0);
            String var15 = UnitUtil.unitProperty(var2, var3, "DebugInformationUsagePolicy");
            var4.setInteger(var15, 0);
         }

         if (var0.kS != null) {
            String var12 = UnitUtil.unitProperty(var2, var3, "ImageBase");
            var4.setString(var12, Long.toHexString(var0.kS) + "h");
            String var16 = UnitUtil.unitProperty(var2, var3, "RelocationBaseForZeroBasedRelocatableObjects");
            var4.setString(var16, Long.toHexString(var0.kS) + "h");
         }

         if (var0.wS != null) {
            String var13 = UnitUtil.unitProperty(var2, var3, "CompilerIdentification");
            var4.setInteger(var13, var0.wS);
         }

         if (var0.UT) {
            String var14 = UnitUtil.unitProperty(var2, var3, "SignaturePackagesLoading");
            var4.setInteger(var14, 0);
         }
      }

      return var1;
   }

   public static void pC(HeadlessClientContext var0, K var1) {
      if (var1 != null) {
         IEnginesContext var2 = var0.getEnginesContext();
         String var3 = var1.pC();
         IPropertyManager var4 = var2.getPropertyManager();
         var4.getConfiguration().clearProperty(UnitUtil.unitProperty(var2, var3, "AnalysisStyle"));
         var4.getConfiguration().clearProperty(UnitUtil.unitProperty(var2, var3, "AdvancedAnalysis"));
         var4.getConfiguration().clearProperty(UnitUtil.unitProperty(var2, var3, "DebugInformationRetrievalPolicy"));
         var4.getConfiguration().clearProperty(UnitUtil.unitProperty(var2, var3, "DebugInformationUsagePolicy"));
         var4.getConfiguration().clearProperty(UnitUtil.unitProperty(var2, var3, "ImageBase"));
         var4.getConfiguration().clearProperty(UnitUtil.unitProperty(var2, var3, "RelocationBaseForZeroBasedRelocatableObjects"));
         var4.getConfiguration().clearProperty(UnitUtil.unitProperty(var2, var3, "CompilerIdentification"));
         var4.getConfiguration().clearProperty(UnitUtil.unitProperty(var2, var3, "SignaturePackagesLoading"));
      }

      var0.stop();
   }

   public static List pC(HeadlessClientContext var0, String var1, String var2) throws IOException {
      IEnginesContext var3 = var0.getEnginesContext();
      String var4 = var1 + File.separator + var2;
      File var5 = new File(var4);
      IRuntimeProject var6 = null;
      if (var5.exists()) {
         try {
            var6 = var3.loadProject(var4);
         } catch (IOException var10) {
            pC.catching(var10);
         }
      }

      ILiveArtifact var7;
      if (var6.getLiveArtifact(0) != null) {
         var7 = var6.getLiveArtifact(0);
      } else {
         File var8 = new File(new File(var1), var2);
         Artifact var9 = new Artifact(var8.getName(), new FileInput(var8));
         var7 = var6.processArtifact(var9);
      }

      return var7.getUnits();
   }
}
