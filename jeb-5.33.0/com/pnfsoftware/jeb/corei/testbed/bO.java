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

public class bO {
   private static final ILogger sY = GlobalLog.getLogger(bO.class);
   String pC;
   String A;
   String kS;
   IEnginesContext wS;
   IRuntimeProject UT;
   List E;

   public bO(String var1, String var2) throws JebException {
      this.pC = var1;
      this.A = var2;
      ICoreContext var3 = JebCoreService.getInstance(null);
      JDB2Manager var4 = new JDB2Manager(var1);
      SimpleFSFileStore var5 = new SimpleFSFileStore(var1);
      CommonsConfigurationWrapper var6 = new CommonsConfigurationWrapper(new BaseConfiguration());
      DataProvider var7 = new DataProvider(null, var4, var5, null, null, var6);
      this.wS = var3.createEnginesContext(var7, null);
      this.kS = var1 + File.separator + var2;
      File var8 = new File(this.kS);
      if (var8.exists()) {
         Object[] var10000 = new Object[]{this.kS};

         try {
            this.UT = this.wS.loadProject(this.kS);
         } catch (IOException var10) {
            sY.catching(var10);
         }

         var10000 = new Object[0];
      }
   }

   public List pC() throws IOException {
      ILiveArtifact var1;
      if (this.UT.getLiveArtifact(0) != null) {
         var1 = this.UT.getLiveArtifact(0);
      } else {
         File var2 = new File(new File(this.pC), this.A);
         Artifact var3 = new Artifact(var2.getName(), new FileInput(var2));
         var1 = this.UT.processArtifact(var3);
      }

      this.E = var1.getUnits();
      return this.E;
   }

   public IEnginesContext A() {
      return this.wS;
   }
}
