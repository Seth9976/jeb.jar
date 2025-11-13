package com.pnfsoftware.jeb.util.encoding.zip;

import java.security.cert.Certificate;

public interface IGenericJarBrowser extends IGenericZipBrowser {
   Certificate[] getCertificates(String var1);
}
