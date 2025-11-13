package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.script.JebPythonPluginService;
import com.pnfsoftware.jeb.core.IPlugin;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import java.io.File;

public class Rd extends Nz {
   JebPythonPluginService Uv;

   public Rd(File var1) throws JebException {
      this(var1, var1);
   }

   public Rd(File var1, File var2) throws JebException {
      super(var1);
      this.Uv = new JebPythonPluginService(var2);
   }

   @Override
   protected String xK() {
      return "*.py";
   }

   @Override
   protected String Dw() {
      return "#";
   }

   @Override
   protected Class q(File var1) throws Exception {
      return this.Uv.getPluginClass(var1.getPath());
   }

   @Override
   protected IPlugin q(Class var1) throws Exception {
      return this.Uv.createPlugin(var1);
   }
}
