package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.explorer;

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
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.aco;
import com.pnfsoftware.jebglobal.ckw;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.configuration2.BaseConfiguration;

public class KD {
   private static final StructuredLogger ys = aco.pC(KD.class);
   String pC;
   String A;
   String kS;
   IEnginesContext wS;
   IRuntimeProject UT;
   List E;
   boolean sY;

   public KD(String var1, String var2) throws JebException {
      this(var1, var2, false, "");
   }

   public KD(String var1, String var2, boolean var3, String var4) throws JebException {
      this.pC = var1;
      this.A = var2;
      this.sY = var3;
      ICoreContext var5 = JebCoreService.getInstance(null);
      JDB2Manager var6 = new JDB2Manager(var1);
      SimpleFSFileStore var7 = new SimpleFSFileStore(var1);
      CommonsConfigurationWrapper var8 = new CommonsConfigurationWrapper(new BaseConfiguration());
      DataProvider var9 = new DataProvider(null, var6, var7, null, null, var8);
      this.wS = var5.createEnginesContext(var9, null);
      if (ckw.pC()) {
         this.wS.getTypeLibraryService().addFolder(new File("C:\\work\\PNF\\jeb2\\jeb3-core\\typelibs"), true);
      } else {
         String var10 = System.getProperty("user.name");
         if (var10 != null && var10.contains("ubuntu")) {
            this.wS.getTypeLibraryService().addFolder(new File("/home/ubuntu/jeb3-core/typelibs"), true);
         }
      }

      this.kS = var1 + File.separator + var2 + var4 + ".jdb2";
      if (var3) {
         File var13 = new File(this.kS);
         if (var13.exists()) {
            Object[] var10000 = new Object[0];

            try {
               this.UT = this.wS.loadProject(this.kS);
            } catch (IOException var12) {
               ys.catching(var12);
            }

            var10000 = new Object[0];
         }
      }
   }

   public List pC() throws JebException, IOException {
      ILiveArtifact var1;
      if (this.sY && this.UT != null) {
         var1 = this.UT.getLiveArtifact(0);
      } else {
         if (this.sY) {
            this.UT = this.wS.loadProject(this.kS);
         } else {
            this.UT = this.wS.loadProject("DummyProject");
         }

         File var2 = new File(new File(this.pC), this.A);
         Artifact var3 = new Artifact(var2.getName(), new FileInput(var2));
         var1 = this.UT.processArtifact(var3);
      }

      this.E = var1.getUnits();
      return this.E;
   }

   public void A() throws IOException {
      if (this.wS.saveProject(this.kS)) {
         Object[] var10000 = new Object[0];
      }
   }
}
