package com.pnfsoftware.jeb.corei.testbed;

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
import com.pnfsoftware.jeb.core.properties.impl.CommonsConfigurationWrapper;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.configuration2.BaseConfiguration;

public class oM {
   private static final ILogger gO = GlobalLog.getLogger(oM.class);
   String q;
   String RF;
   String xK;
   IEnginesContext Dw;
   IRuntimeProject Uv;
   List oW;

   public oM(String var1, String var2) throws JebException {
      this.q = var1;
      this.RF = var2;
      ICoreContext var3 = JebCoreService.getInstance(null);
      JDB2Manager var4 = new JDB2Manager(var1);
      SimpleFSFileStore var5 = new SimpleFSFileStore(var1);
      CommonsConfigurationWrapper var6 = new CommonsConfigurationWrapper(new BaseConfiguration());
      DataProvider var7 = new DataProvider(null, var4, var5, null, null, var6);
      this.Dw = var3.createEnginesContext(var7, null);
      this.xK = var1 + File.separator + var2;
      File var8 = new File(this.xK);
      if (var8.exists()) {
         Object[] var10000 = new Object[]{this.xK};

         try {
            this.Uv = this.Dw.loadProject(this.xK);
         } catch (IOException var10) {
            gO.catching(var10);
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
