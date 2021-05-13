<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
			<body>
				<center>
					<h2>XSLT transformation example</h2>
					<table border="1">
						<tr bgcolor="grey" style="background: #5599CC; color: white;">
							<th style="padding: 10px">Id</th>
							<th style="padding: 10px">Plan</th>
							<th style="padding: 10px">Cable</th>
							<th style="padding: 10px">Internet</th>
							<th style="padding: 10px">Phone</th>
						</tr>
						<xsl:for-each select="subscriptions">
							<tr>
								<td style="padding: 10px">
									<xsl:value-of select="id" />
								</td>
								<td style="padding: 10px">
									<xsl:value-of select="name" />
								</td>

								<xsl:if test="cableSubscribed='true'">
									<td style="padding: 10px">Active</td>
								</xsl:if>
								<xsl:if test="cableSubscribed='false'">
									<td style="padding: 10px">Not Active</td>
								</xsl:if>

								<xsl:if test="internetSubscribed='true'">
									<td style="padding: 10px">Active</td>
								</xsl:if>
								<xsl:if test="internetSubscribed='false'">
									<td style="padding: 10px">Not Active</td>
								</xsl:if>

								<xsl:if test="phoneSubscribed='true'">
									<td style="padding: 10px">Active</td>
								</xsl:if>
								<xsl:if test="phoneSubscribed='false'">
									<td style="padding: 10px">Not Active</td>
								</xsl:if>

							</tr>
						</xsl:for-each>
					</table>
				</center>

			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>