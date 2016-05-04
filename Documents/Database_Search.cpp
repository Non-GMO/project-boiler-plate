// the previous function confirmed that the UPC is in the database
// this function searches the database and returns the stuff we need
#include<iostream>
#include<string>
#include<vector>
#include<fstream>
#include<algorithm>
using namespace std;

struct Database
{
	string productName;
	string brandName;
	string UPC;
	int organic = -1;
};

//trims whitespace from inputs. whitespace is the enemy
string trim(string &string)
{
	size_t first = string.find_first_not_of(' ');
	size_t last = string.find_last_not_of(' ');
	return string.substr(first, (last - first + 1));
}

int main()
{
	ifstream infile("Search_With_UPC.txt");
	bool done = false;
	string targetUPC;
	string seperator = "//";

	cout << "enter target UPC " << endl;
	cin >> targetUPC;
	trim(targetUPC);
	//cout << "target UPC: " << targetUPC << endl;

	if (infile.is_open())
	{
		vector<Database> database;
		database.resize(100);

		while (done == false)
		{
			// reads in items in sets of 100 into the vector
			for (int i = 0; i < 100; i++)
			{
				string in;
				string brandName = "";
				string productName = "";
				string organic = "";
				string UPC = "";

				while (in != seperator)
				{
					infile >> in;
					if (in != seperator)
					{
						productName = productName + " " + in;
					}
				}
				trim(productName);
				database.at(i).productName = productName;
				// cout << database.at(i).productName << endl;

				in = "";
				while (in != seperator)
				{
					infile >> in;
					if (in != seperator)
					{
						brandName = brandName + " " + in;
					}
				}
				trim(brandName);
				database.at(i).brandName = brandName;
				
				// if entry read in is "No" set the attibute to false, if "Yes" set to true
				in = "";
				while (in != seperator)
				{
					infile >> in;
					if (in != seperator)
					{
						organic = in;
					}
				}
				trim(organic);
				if (organic == "No")
				{
					database.at(i).organic = 0;
				}
				else if (organic == "Yes")
				{
					database.at(i).organic = 1;
				}
				else if (organic == "<none>")
				{
					database.at(i).organic = -1;
				}
				else
				{
					cout << "Error. Organic set error for item  " << in << " for item : " << database.at(i).productName << " , " << database.at(i).brandName << endl;
					database.at(i).organic = -1;
				}

				in = "";
				while (in != seperator)
				{
					infile >> in;
					if (in != seperator)
					{
						UPC = in;
					}
				}
				trim(UPC);
				database.at(i).UPC = UPC;
				
				if (UPC == targetUPC)
				{
					cout << "Product name: " << database.at(i).productName << endl;
					cout << "Brand name: " << database.at(i).brandName << endl;
					cout << "UPC code: " << database.at(i).UPC << endl;
					cout << "Organic: " << database.at(i).organic << endl;
					done = true;
					break;
				}
			}
		}
	}
	else
	{
		cout << "unable to open file" << endl;
	}
	return 0;
}
