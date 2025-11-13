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
import com.pnfsoftware.jebglobal.aeg;
import com.pnfsoftware.jebglobal.cvl;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.configuration2.BaseConfiguration;

public class qV {
   private static final StructuredLogger nf = aeg.q(qV.class);
   String q;
   String RF;
   String xK;
   IEnginesContext Dw;
   IRuntimeProject Uv;
   List oW;
   boolean gO;

   public qV(String var1, String var2) throws JebException {
      this(var1, var2, false, "");
   }

   public qV(String var1, String var2, boolean var3, String var4) throws JebException {
      this.q = var1;
      this.RF = var2;
      this.gO = var3;
      ICoreContext var5 = JebCoreService.getInstance(null);
      JDB2Manager var6 = new JDB2Manager(var1);
      SimpleFSFileStore var7 = new SimpleFSFileStore(var1);
      CommonsConfigurationWrapper var8 = new CommonsConfigurationWrapper(new BaseConfiguration());
      DataProvider var9 = new DataProvider(null, var6, var7, null, null, var8);
      this.Dw = var5.createEnginesContext(var9, null);
      if (cvl.RF()) {
         this.Dw.getTypeLibraryService().addFolder(new File("C:\\work\\PNF\\jeb2\\jeb3-core\\typelibs"), true);
      } else {
         String var10 = System.getProperty("user.name");
         if (var10 != null && var10.contains("ubuntu")) {
            this.Dw.getTypeLibraryService().addFolder(new File("/home/ubuntu/jeb3-core/typelibs"), true);
         }
      }

      this.xK = var1 + File.separator + var2 + var4 + ".jdb2";
      if (var3) {
         File var13 = new File(this.xK);
         if (var13.exists()) {
            Object[] var10000 = new Object[0];

            try {
               this.Uv = this.Dw.loadProject(this.xK);
            } catch (IOException var12) {
               nf.catching(var12);
            }

            var10000 = new Object[0];
         }
      }
   }

   public List q() throws JebException, IOException {
      ILiveArtifact var1;
      if (this.gO && this.Uv != null) {
         var1 = this.Uv.getLiveArtifact(0);
      } else {
         if (this.gO) {
            this.Uv = this.Dw.loadProject(this.xK);
         } else {
            this.Uv = this.Dw.loadProject("DummyProject");
         }

         File var2 = new File(new File(this.q), this.RF);
         Artifact var3 = new Artifact(var2.getName(), new FileInput(var2));
         var1 = this.Uv.processArtifact(var3);
      }

      this.oW = var1.getUnits();
      return this.oW;
   }

   public void RF() throws IOException {
      if (this.Dw.saveProject(this.xK)) {
         Object[] var10000 = new Object[0];
      }
   }

   public IEnginesContext xK() {
      return this.Dw;
   }

   public IRuntimeProject Dw() {
      return this.Uv;
   }

   public List Uv() {
      return this.oW;
   }

   public void oW() throws JebException {
      JebCoreService.getInstance().closeEnginesContext(this.Dw);
   }
}
