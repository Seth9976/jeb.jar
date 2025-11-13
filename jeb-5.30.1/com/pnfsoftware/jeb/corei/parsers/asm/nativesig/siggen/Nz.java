package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.core.Artifact;
import com.pnfsoftware.jeb.core.ICoreContext;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.ILiveArtifact;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.dao.impl.DataProvider;
import com.pnfsoftware.jeb.core.dao.impl.JDB2Manager;
import com.pnfsoftware.jeb.core.dao.impl.SimpleFSFileStore;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.CommonsConfigurationWrapper;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.configuration2.BaseConfiguration;

public class Nz {
   private static final ILogger gO = GlobalLog.getLogger(Nz.class);
   String q;
   String RF;
   String xK;
   IEnginesContext Dw;
   IRuntimeProject Uv;
   List oW;

   public Nz(String var1, String var2, com.pnfsoftware.jeb.corei.parsers.asm.nativesig.nI var3) throws JebException {
      this.q = var1;
      this.RF = var2;
      ICoreContext var4 = JebCoreService.getInstance(null);
      JDB2Manager var5 = new JDB2Manager(var1);
      SimpleFSFileStore var6 = new SimpleFSFileStore(var1);
      CommonsConfigurationWrapper var7 = new CommonsConfigurationWrapper(new BaseConfiguration());
      DataProvider var8 = new DataProvider(null, var5, var6, null, null, var7);
      this.Dw = var4.createEnginesContext(var8, null);
      IPropertyManager var9 = this.Dw.getPropertyManager();
      HashSet var10 = Sets.newHashSet("x86", "x86_64", "arm", "arm64", "mips", "mips64");
      int var11 = var3.RF();

      for (String var13 : var10) {
         var9.setInteger(UnitUtil.unitProperty(this.Dw, var13, "AnalysisStyle"), var11);
      }

      int var16 = var3.xK();

      for (String var14 : var10) {
         var9.setInteger(UnitUtil.unitProperty(this.Dw, var14, "AdvancedAnalysis"), var16);
      }

      this.Dw.getTypeLibraryService().addFolder(new File(var3.Dw()), true);
      this.xK = var1 + File.separator + var2;
      File var18 = new File(this.xK);
      if (var18.exists()) {
         Object[] var10000 = new Object[]{this.xK};

         try {
            this.Uv = this.Dw.loadProject(this.xK);
         } catch (IOException var15) {
            gO.catching(var15);
         }

         var10000 = new Object[0];
      }
   }

   public List q() throws IOException {
      ILiveArtifact var1;
      if (this.Uv.getLiveArtifact(0) != null) {
         var1 = this.Uv.getLiveArtifact(0);
      } else {
         File var2 = new File(new File(this.q), this.RF);
         Artifact var3 = new Artifact(var2.getName(), new FileInput(var2));
         var1 = this.Uv.processArtifact(var3);
      }

      this.oW = var1.getUnits();
      return this.oW;
   }

   public IEnginesContext RF() {
      return this.Dw;
   }

   public IRuntimeProject xK() {
      return this.Uv;
   }

   public List Dw() {
      return this.oW;
   }

   public void Uv() throws JebException {
      JebCoreService.getInstance().closeEnginesContext(this.Dw);
   }
}
