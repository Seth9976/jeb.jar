package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.script.JebPythonPluginService;
import com.pnfsoftware.jeb.core.IPlugin;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import java.io.File;

public class Ee extends jY {
   JebPythonPluginService UT;

   public Ee(File var1) throws JebException {
      this(var1, var1);
   }

   public Ee(File var1, File var2) throws JebException {
      super(var1);
      this.UT = new JebPythonPluginService(var2);
   }

   @Override
   protected String kS() {
      return "*.py";
   }

   @Override
   protected String wS() {
      return "#";
   }

   @Override
   protected Class pC(File var1) throws Exception {
      return this.UT.getPluginClass(var1.getPath());
   }

   @Override
   protected IPlugin pC(Class var1) throws Exception {
      return this.UT.createPlugin(var1);
   }
}
