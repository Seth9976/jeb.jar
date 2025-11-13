package com.pnfsoftware.jeb.client.script;

import com.pnfsoftware.jeb.client.S;
import java.io.IOException;
import java.io.Reader;

class JebPythonInterpreter$1 extends Reader {
   JebPythonInterpreter$1(JebPythonInterpreter var1) {
      this.this$0 = var1;
   }

   @Override
   public int read(char[] var1, int var2, int var3) throws IOException {
      JebPythonInterpreter.logger.error(S.L("This interpreter does not accept input from stdin at the moment"));
      return -1;
   }

   @Override
   public void close() throws IOException {
   }
}
