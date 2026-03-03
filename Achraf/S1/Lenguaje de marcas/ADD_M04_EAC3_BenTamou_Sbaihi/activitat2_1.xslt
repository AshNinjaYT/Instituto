<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" encoding="UTF-8" />

	<xsl:template match="/">
		<html>
			<head>
				<title>Amants del cinema -- Tona</title>
				<style>
					body {
					font-family: Arial, sans-serif;
					}
					h2 {
					font-weight: normal;
					padding-bottom: 10px;
					}
					table {
					width: 100%;
					border-collapse: collapse;
					margin-top: 10px;
					}
					th {
					background-color: #2f3542;
					color: white;
					padding: 10px;
					text-align: center;
					font-weight: bold;
					}
					td {
					padding: 10px;
					text-align: center;
					border-bottom: 1px solid #ccc;
					}
					.italic {
					font-style: italic;
					}
					.bold {
					font-weight: bold;
					}
				</style>
			</head>
			<body>
				<h2>
					-- <xsl:value-of select="CINECLUB/nom" /> -- <xsl:value-of select="CINECLUB/municipi" />
				</h2>

				<table>
					<tr>
						<th>TÍTOL</th>
						<th>DIRECTOR / país</th>
						<th>GÈNERE</th>
					</tr>

					<xsl:for-each select="CINECLUB/pelicula">
						<tr>
							<td class="bold">
								<xsl:value-of select="titol" />
							</td>
							<td>
								<span class="bold">
									<xsl:value-of select="director" />
								</span>
								<span class="italic">
									/ <xsl:value-of select="director/@pais" />
								</span>
							</td>
							<td>
								<xsl:value-of select="genere" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
