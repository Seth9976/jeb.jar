package com.pnfsoftware.jeb.util.net;

import com.pnfsoftware.jeb.util.base.IProgressCallback;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface INet {
   void setUserAgent(String var1);

   String getUserAgent();

   void setConnectTimeout(int var1);

   int getConnectTimeout();

   void setReadTimeout(int var1);

   int getReadTimeout();

   void setWriteTimeout(int var1);

   int getWriteTimeout();

   SecureSocketInfo getSecureSocketInfo();

   void setSecureSocketInfo(SecureSocketInfo var1);

   INet duplicate();

   String query(String var1, Map var2, Map var3) throws IOException;

   String query(String var1, Map var2) throws IOException;

   String query(String var1) throws IOException;

   byte[] queryBinary(String var1, Map var2, Map var3, IProgressCallback var4) throws IOException;

   byte[] queryBinary(String var1, Map var2, Map var3) throws IOException;

   byte[] queryBinary(String var1, Map var2) throws IOException;

   byte[] queryBinary(String var1) throws IOException;

   long downloadBinary(File var1, String var2, Map var3, Map var4, IProgressCallback var5) throws IOException;

   String post(String var1, Map var2, Map var3, Map var4) throws IOException;

   String post(String var1, Map var2, Map var3) throws IOException;

   String postMultipart(String var1, Map var2, Map var3, Map var4) throws IOException;

   String postMultipart(String var1, Map var2, Map var3) throws IOException;
}
