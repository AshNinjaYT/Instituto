<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes" />

  <xsl:template match="/">
    <html>
      <head>
        <title>Amants del cinema</title>
        <style>
          body {
          font-family: Arial, sans-serif;
          margin: 20px;
          }
          table {
          width: 100%;
          border-collapse: collapse;
          margin-top: 20px;
          }
          th {
          background-color: #444;
          color: white;
          text-align: center;
          padding: 10px;
          }
          td {
          border: 1px solid #ccc;
          padding: 10px;
          vertical-align: top;
          }
          td img {
          max-width: 100px;
          }
          .actors ul {
          padding-left: 20px;
          margin: 0;
          }
          .actors li {
          list-style-type: disc;
          }
        </style>
      </head>
      <body>
        <h2>-- <xsl:value-of select="CINECLUB/nom" /> -- <xsl:value-of select="CINECLUB/municipi" /></h2>

        <table>
          <tr>
            <th>PORTADA</th>
            <th>TÍTOL</th>
            <th>DIRECTOR</th>
            <th>GÈNERE</th>
            <th>COMPANYIA</th>
            <th>ACTORS</th>
          </tr>

          <xsl:for-each select="CINECLUB/pelicula">
            <tr>
              <td>
                <img>
                  <xsl:attribute name="src">
                    <xsl:value-of select="portada" />
                  </xsl:attribute>
                  <xsl:attribute name="alt">
                    <xsl:value-of select="titol" />
                  </xsl:attribute>
                </img>
              </td>
              <td>
                <xsl:value-of select="titol" />
              </td>
              <td>
                <i><xsl:value-of select="director" /> / <xsl:value-of select="director/@pais" /></i>
              </td>
              <td>
                <xsl:value-of select="genere" />
              </td>
              <td>
                <xsl:value-of select="companyia" />
              </td>
              <td class="actors">
                <ul>
                  <xsl:for-each select="actors/actor">
                    <li>
                      <xsl:value-of select="." />
                    </li>
                  </xsl:for-each>
                </ul>
              </td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>